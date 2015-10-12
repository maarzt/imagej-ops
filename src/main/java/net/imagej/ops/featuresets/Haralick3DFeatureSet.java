/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2015 Board of Regents of the University of
 * Wisconsin-Madison, University of Konstanz and Brian Northan.
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
package net.imagej.ops.featuresets;

import java.util.Collection;
import java.util.HashSet;

import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import net.imagej.ops.Contingent;
import net.imagej.ops.OpRef;
import net.imagej.ops.Ops.Haralick.ASM;
import net.imagej.ops.Ops.Haralick.ClusterPromenence;
import net.imagej.ops.Ops.Haralick.ClusterShade;
import net.imagej.ops.Ops.Haralick.Contrast;
import net.imagej.ops.Ops.Haralick.Correlation;
import net.imagej.ops.Ops.Haralick.DifferenceEntropy;
import net.imagej.ops.Ops.Haralick.DifferenceVariance;
import net.imagej.ops.Ops.Haralick.Entropy;
import net.imagej.ops.Ops.Haralick.ICM1;
import net.imagej.ops.Ops.Haralick.ICM2;
import net.imagej.ops.Ops.Haralick.IFDM;
import net.imagej.ops.Ops.Haralick.MaxProbability;
import net.imagej.ops.Ops.Haralick.SumAverage;
import net.imagej.ops.Ops.Haralick.SumEntropy;
import net.imagej.ops.Ops.Haralick.SumVariance;
import net.imagej.ops.Ops.Haralick.TextureHomogeneity;
import net.imagej.ops.Ops.Haralick.Variance;
import net.imagej.ops.features.haralick.HaralickFeature;
import net.imagej.ops.image.cooccurrencematrix.MatrixOrientation3D;
import net.imglib2.IterableInterval;

/**
 * {@link FeatureSet} for {@link HaralickFeature}s
 * 
 * @author Christian Dietz, University of Konstanz
 *
 * @param <T>
 * @param <O>
 */
@Plugin(type = FeatureSet.class, label = "3D Haralick Features", description = "Calculates the 3D Haralick Features")
public class Haralick3DFeatureSet<T, O> extends AbstractOpRefFeatureSet<IterableInterval<T>, O>
		implements Contingent, IntensityFeatureSet<IterableInterval<T>, O> {

	@Parameter(type = ItemIO.INPUT, label = "Num. Grey Levels", description = "The number of grey values determines the size of the co-occurence matrix on which the Haralick features are calculated.", min = "1", max = "2147483647", stepSize = "1")
	private int numGreyLevels = 32;

	@Parameter(type = ItemIO.INPUT, label = "Distance", description = "The maximum distance between pairs of pixels which will be added to the co-occurence matrix.", min = "1", max = "2147483647", stepSize = "1")
	private int distance = 1;

	@Parameter(type = ItemIO.INPUT, label = "Orientation", description = "Orientation of the pairs of pixels which will be added to the co-occurence matrix", choices = {
			"HORIZONTAL", "VERTICAL", "DIAGONAL", "ANTIDIAGONAL", "HORIZONTAL_VERTICAL", "HORIZONTAL_DIAGONAL",
			"VERTICAL_VERTICAL", "VERTICAL_DIAGONAL", "DIAGONAL_VERTICAL", "DIAGONAL_DIAGONAL", "ANTIDIAGONAL_VERTICAL",
			"ANTIDIAGONAL_DIAGONAL", "DEPTH" })
	private String orientation = "HORIZONTAL";

	@Override
	protected Collection<? extends OpRef<?>> initOpRefs() {
		final HashSet<OpRef<?>> refs = new HashSet<OpRef<?>>();

		MatrixOrientation3D matrixOrientation3D = MatrixOrientation3D.valueOf(orientation);

		refs.add(ref(ASM.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(ClusterPromenence.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(ClusterShade.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(Contrast.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(Correlation.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(DifferenceEntropy.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(DifferenceVariance.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(Entropy.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(ICM1.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(ICM2.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(IFDM.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(MaxProbability.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(SumAverage.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(SumEntropy.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(SumVariance.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(TextureHomogeneity.class, numGreyLevels, distance, matrixOrientation3D));
		refs.add(ref(Variance.class, numGreyLevels, distance, matrixOrientation3D));

		return refs;
	}

	@Override
	public boolean conforms() {
		return in().numDimensions() == 3;
	}

	@Override
	public int getMinDimensions() {
		return 3;
	}

	@Override
	public int getMaxDimensions() {
		return 3;
	}

}