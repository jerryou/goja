/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package com.jfinal.ext.plugin.quartz;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * <p>
 * Job annotation.
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-01-19 22:56
 * @since JDK 1.6
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface On {

    String name() default "job";

    String value();

    boolean enabled() default true;
}
