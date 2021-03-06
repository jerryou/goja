/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package goja;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;
import com.jfinal.kit.PathKit;
import goja.app.StringPool;
import goja.init.ConfigProperties;
import goja.init.InitConst;
import goja.logging.AppLogConfigurator;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static goja.init.InitConst.APP;
import static goja.init.InitConst.APP_VERSION;

/**
 * <p>
 * Main logger for the application.
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-06-01 18:00
 * @since JDK 1.6
 */
public class Logger {
    /**
     * Will record and display the caller method.
     */
    public static boolean recordCaller = false;
    /**
     * The application logger (japp).
     */
    public static org.slf4j.Logger slf4j;

    /**
     * true if logger is configured manually (log4j-config file supplied by application)
     */
    public static boolean configuredManually = false;

    /**
     * Try to init stuff.
     */
    public static void init() {
        String slf4jPath = ConfigProperties.getProperty(InitConst.LOGGER_PATH, "/logback.xml");
        URL slf4jConf = Logger.class.getResource(slf4jPath);
        final String app_name = ConfigProperties.getProperty(APP, "app");
        final String app_version = ConfigProperties.getProperty(APP_VERSION, "0.0.1");
        if (slf4jConf == null) {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

            StatusManager statusManager = lc.getStatusManager();
            OnConsoleStatusListener onConsoleListener = new OnConsoleStatusListener();
            statusManager.add(onConsoleListener);
            AppLogConfigurator.configure(lc);

            Logger.slf4j = org.slf4j.LoggerFactory.getLogger(app_name + StringPool.AT + app_version);
        } else if (Logger.slf4j == null) {

            if (slf4jConf.getFile().indexOf(PathKit.getWebRootPath()) == 0) {
                // The log4j configuration file is located somewhere in the application folder,
                // so it's probably a custom configuration file
                configuredManually = true;
            }
            Logger.slf4j = org.slf4j.LoggerFactory.getLogger(app_name + StringPool.AT + app_version);

        }
    }


    /**
     * @return true if log4j.debug / jul.fine logging is enabled
     */
    public static boolean isDebugEnabled() {

        return slf4j.isDebugEnabled();
    }


    /**
     * Log with DEBUG level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void debug(String message, Object... args) {

        if (recordCaller) {
            LoggerFactory.getLogger(getCallerClassName()).debug(message, args);
        } else {
            slf4j.debug(message, args);
        }

    }

    /**
     * Log with DEBUG level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void debug(Throwable e, String message, Object... args) {

        if (recordCaller) {
            LoggerFactory.getLogger(getCallerClassName()).debug(message, args, e);
        } else {
            slf4j.debug(message, args, e);
        }
    }

    /**
     * Log with INFO level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */

    public static void info(String message, Object... args) {

        if (recordCaller) {
            LoggerFactory.getLogger(getCallerClassName()).info(message, args);
        } else {
            slf4j.info(message, args);
        }
    }

    /**
     * Log with INFO level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void info(Throwable e, String message, Object... args) {

        if (recordCaller) {
            LoggerFactory.getLogger(getCallerClassName()).info(message, args, e);
        } else {
            slf4j.info(message, args, e);
        }
    }

    /**
     * Log with WARN level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void warn(String message, Object... args) {

        try {
            if (recordCaller) {
                LoggerFactory.getLogger(getCallerClassName()).warn(message, args);
            } else {
                slf4j.warn(message, args);
            }
        } catch (Throwable ex) {
            slf4j.error("Oops. Error in Logger !", ex);
        }
    }

    /**
     * Log with WARN level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void warn(Throwable e, String message, Object... args) {

        try {
            if (recordCaller) {
                LoggerFactory.getLogger(getCallerClassName()).warn(message, args, e);
            } else {
                slf4j.warn(message, args, e);
            }
        } catch (Throwable ex) {
            slf4j.error("Oops. Error in Logger !", ex);
        }
    }

    public static boolean isErrorEnabled() {

        try {
            if (recordCaller) {
                return LoggerFactory.getLogger(getCallerClassName()).isErrorEnabled();
            } else {
                return slf4j.isErrorEnabled();
            }
        } catch (Throwable ex) {
            slf4j.error("Oops. Error in Logger !", ex);
            return false;
        }

    }

    /**
     * Log with ERROR level
     *
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void error(String message, Object... args) {

        try {
            if (recordCaller) {
                LoggerFactory.getLogger(getCallerClassName()).error(message, args);
            } else {
                slf4j.error(message, args);
            }
        } catch (Throwable ex) {
            slf4j.error("Oops. Error in Logger !", ex);
        }

    }

    /**
     * Log with ERROR level
     *
     * @param e       the exception to log
     * @param message The message pattern
     * @param args    Pattern arguments
     */
    public static void error(Throwable e, String message, Object... args) {

        if (recordCaller) {
            LoggerFactory.getLogger(getCallerClassName()).error(message, args, e);
        } else {
            slf4j.error(message, args, e);
        }
    }


    public static boolean isWarnEnabled() {

        try {
            if (recordCaller) {
                return LoggerFactory.getLogger(getCallerClassName()).isWarnEnabled();
            } else {
                return slf4j.isWarnEnabled();
            }
        } catch (Throwable ex) {
            slf4j.error("Oops. Error in Logger !", ex);
            return false;
        }
    }


    /**
     * @return the className of the class actually logging the message
     */
    static String getCallerClassName() {
        final int level = 5;
        return getCallerClassName(level);
    }

    /**
     * @return the className of the class actually logging the message
     */
    static String getCallerClassName(final int level) {
        CallInfo ci = getCallerInformations(level);
        return ci.className;
    }

    /**
     * Examine stack trace to get caller
     *
     * @param level method stack depth
     * @return who called the logger
     */
    static CallInfo getCallerInformations(int level) {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StackTraceElement caller = callStack[level];
        return new CallInfo(caller.getClassName(), caller.getMethodName());
    }

    /**
     * Info about the logger caller
     */
    static class CallInfo {

        public String className;
        public String methodName;

        public CallInfo() {
        }

        public CallInfo(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }
    }
}