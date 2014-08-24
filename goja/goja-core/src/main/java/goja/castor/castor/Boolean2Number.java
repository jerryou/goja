/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package goja.castor.castor;


import goja.castor.Castor;
import goja.lang.Lang;
import goja.lang.Mirror;

public class Boolean2Number extends Castor<Boolean, Number> {

    @Override
    public Number cast(Boolean src, Class<?> toType, String... args) {
        try {
            return (Number) Mirror.me(toType)
                    .getWrapperClass()
                    .getConstructor(String.class)
                    .newInstance(src ? "1" : "0");
        } catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
    }

}
