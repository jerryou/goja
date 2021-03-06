/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package org.lionsoul.jcseg;

import org.lionsoul.jcseg.core.IChunk;
import org.lionsoul.jcseg.core.ILastRule;
import org.lionsoul.jcseg.core.IRule;

/**
 * the last rule.
 * 		-clear the ambiguity after the four rule.
 * 
 * @author	chenxin<chenxin619315@gmail.com>
 */
public class LASTRule implements ILastRule {
	
	/**
	 * maxmum match rule instance.
	 */
	private static LASTRule __instance = null;
	
	/**
	 * return the quote to the maximum match instance.
	 * 
	 * @return MMRule
	 */
	public static LASTRule createRule() {
		if ( __instance == null )
			__instance = new LASTRule();
		return __instance;
	}
	
	private LASTRule() {}

	/**
	 * last rule interface.
	 * here we simply return the first chunk.
	 * 
	 * @see org.lionsoul.jcseg.core.IRule#call(org.lionsoul.jcseg.core.IChunk[])
	 */
	@Override
	public IChunk call(IChunk[] chunks) {
		return chunks[0];
	}

}
