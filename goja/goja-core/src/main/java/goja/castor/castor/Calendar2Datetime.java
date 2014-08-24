/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package goja.castor.castor;

import goja.castor.Castor;
import goja.castor.FailToCastObjectException;

import java.util.Calendar;
import java.util.Date;


public class Calendar2Datetime extends Castor<Calendar, Date> {

    @Override
    public Date cast(Calendar src, Class<?> toType, String... args)
            throws FailToCastObjectException {
        return src.getTime();
    }

}
