/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package goja.castor.castor;


import goja.castor.Castor;

@SuppressWarnings({"rawtypes"})
public class Class2String extends Castor<Class, String> {

    @Override
    public String cast(Class src, Class<?> toType, String... args) {
        return src.getName();
    }

}
