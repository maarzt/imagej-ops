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

package org.scijava.type;

import java.lang.reflect.Type;

import org.scijava.plugin.HandlerPlugin;
import org.scijava.plugin.Plugin;

/**
 * A plugin for extracting generic {@link Type} from instances at runtime.
 * <p>
 * This is an extensible way to achieve quasi-preservation of generic types at
 * runtime, for types which do not normally support it.
 * </p>
 * <p>
 * Type extractor plugins discoverable at runtime must implement this interface
 * and be annotated with @{@link Plugin} with attribute {@link Plugin#type()
 * type} = {@link TypeExtractor}.class.
 * </p>
 * 
 * @author Curtis Rueden
 */
public interface TypeExtractor extends HandlerPlugin<Object> {

	/**
	 * Extracts the generic type of the given object.
	 * 
	 * @return The object's generic {@link Type}, or {@code null} if the object is
	 *         not supported by this extractor.
	 */
	Type typeOf(Object o);

	// -- Typed methods --

	@Override
	default Class<Object> getType() {
		return Object.class;
	}
}
