/*
 * #%L
 * SciJava Common shared library for SciJava software.
 * %%
 * Copyright (C) 2009 - 2016 Board of Regents of the University of
 * Wisconsin-Madison, Broad Institute of MIT and Harvard, and Max Planck
 * Institute of Molecular Cell Biology and Genetics.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imagej.type;

import java.lang.reflect.Type;

import org.scijava.Priority;
import org.scijava.plugin.AbstractHandlerPlugin;
import org.scijava.plugin.Plugin;
import org.scijava.type.GenericTyped;
import org.scijava.type.TypeExtractor;

/**
 * {@link TypeExtractor} plugin which operates on {@link Img} objects.
 * <p>
 * Such objects make life easy for us: they simply declare the {@link Type} by
 * which they wish to be known, and our job is done.
 * </p>
 *
 * @author Curtis Rueden
 */
@Plugin(type = TypeExtractor.class, priority = Priority.HIGH_PRIORITY)
public class ImgExtractor extends AbstractHandlerPlugin<Object>
	implements TypeExtractor
{

	// -- TypeExtractor methods --

	@Override
	public Type typeOf(final Object o) {
		return ((TypeExtractor) o).getType();
	}

	// -- Typed methods --

	@Override
	public boolean supports(final Object o) {
		return o instanceof TypeExtractor;
	}

}
