/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */
package goja.app.interceptor.syslog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import goja.app.StringPool;
import goja.app.interceptor.syslog.config.LogPathConfig;
import goja.app.mvc.kit.Servlets;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SysLogInterceptor implements Interceptor {
    private static final Map<String, LogConfig> acitonLogs   = Maps.newConcurrentMap();
    private              LogProcessor           logProcesser = null;

    public SysLogInterceptor setLogProcesser(LogProcessor logProcesser, String path) {
        this.logProcesser = logProcesser;

        try {
            String config = Files.toString(new File(path), Charset.forName(StringPool.UTF_8));

            if (Strings.isNullOrEmpty(config)) {
                return null;
            }

            List<LogPathConfig> pathConfigs = JSON.parseObject(config, new TypeReference<List<LogPathConfig>>() {
            });
            if (pathConfigs != null && !pathConfigs.isEmpty()) {
                for (LogPathConfig pathConfig : pathConfigs) {
                    final LogConfig logConfig = (Strings.isNullOrEmpty(pathConfig.getFormat()))
                            ? new LogConfig(pathConfig.getTitle())
                            : new LogConfig(pathConfig.getTitle(), pathConfig.getFormat());
                    Map<String, String> params = pathConfig.getParams();
                    if (params != null && !params.isEmpty()) {
                        for (String _p : params.keySet()) {
                            logConfig.addPara(_p, params.get(_p));
                        }
                    }
                    this.addConfig(pathConfig.getPath(), logConfig);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SysLogInterceptor addConfig(String actionKey, LogConfig log) {
        acitonLogs.put(actionKey, log);
        return this;
    }

    @Override
    public void intercept(ActionInvocation ai) {
        String actionKey = ai.getActionKey();
        LogConfig log = acitonLogs.get(actionKey);
        if (log != null) {
            Controller c = ai.getController();
            logFromConfig(c, log);
        }
        ai.invoke();
    }

    private void logFromConfig(Controller c, LogConfig log) {
        final SysLog sysLog = new SysLog();
        sysLog.ip = Servlets.getIp(c.getRequest());
        sysLog.user = logProcesser.getUsername(c);
        Map<String, String[]> parameterMap = c.getRequest().getParameterMap();
        Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
        Map<String, String> paraMap = Maps.newHashMap();
        String _key, _value, _result;
        for (Entry<String, String[]> entry : entrySet) {
            _key = entry.getKey();
            _value = entry.getValue()[0];
            _result = log.params.get(_key);
            if (StringUtils.isBlank(_result))
                continue;
            paraMap.put(_result, _value);
        }
        sysLog.message = logProcesser.formatMessage(log, paraMap);
        sysLog.title = log.getTitle();
        logProcesser.process(sysLog);
    }

}
