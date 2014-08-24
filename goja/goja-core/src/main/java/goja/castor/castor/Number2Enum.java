/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package goja.castor.castor;

import goja.castor.Castor;
import goja.castor.FailToCastObjectException;
import goja.lang.Lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


@SuppressWarnings({"rawtypes"})
public class Number2Enum extends Castor<Number, Enum> {

    @Override
    public Enum cast(Number src, Class<?> toType, String... args)
            throws FailToCastObjectException {
        int v = src.intValue();
        Enum o = null;

        // 首先试图用采用该类型的 fromInt 的静态方法
        try {
            Method m = toType.getMethod("fromInt", int.class);
            if (Modifier.isStatic(m.getModifiers())
                && toType.isAssignableFrom(m.getReturnType())) {
                o = (Enum) m.invoke(null, v);
            }
        }
        catch (Exception e) {}

        // 搞不定，则试图根据顺序号获取
        if (null == o)
            try {
                for (Field field : toType.getFields()) {
                    if (field.getType() == toType) {
                        Enum em = (Enum) field.get(null);
                        if (em.ordinal() == v)
                            return em;
                    }
                }
                throw Lang.makeThrow(FailToCastObjectException.class,
                        "Can NO find enum value in [%s] by int value '%d'",
                        toType.getName(),
                        src.intValue());
            }
            catch (Exception e2) {
                throw Lang.wrapThrow(e2);
            }

        return o;
    }
}
