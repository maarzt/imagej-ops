package net.imagej.ops.topology.eulerCharacteristic;

import net.imagej.ops.OpService;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.logic.BitType;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.scijava.Context;

import java.util.concurrent.TimeUnit;

@Fork(0)
@Warmup( iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS )
@Measurement( iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS )
@State( Scope.Benchmark )
public class EulerCharacteristic26NBenchmark
{
	private Img< BitType > image = ArrayImgs.bits( 100, 100, 100 );

	private final OpService ops = new Context().service( OpService.class );


	@Benchmark
	public double benchmark() {
		return ops.topology().eulerCharacteristic26NFloating(image).get();
	}

	public static void main(String... args) throws RunnerException
	{
		final Options options = new OptionsBuilder()
				.include( EulerCharacteristic26NBenchmark.class.getName() ).build();
		new Runner( options ).run();
	}
}
