package dev.jaderss.algorithms.benchmarks;

import dev.jaderss.algorithms.heap.KthLargest;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class KthLargestBenchmark {

    @Param({"10000", "1000000"})
    int size;

    @Param({"10", "half"})
    String rank;

    int[] numbers;
    int k;

    @Setup
    public void generate() {
        numbers = new SplittableRandom(42).ints(size).toArray();
        k = rank.equals("half") ? size / 2 : Integer.parseInt(rank);
    }

    @Benchmark
    public int heap() {
        return KthLargest.byHeap(numbers, k);
    }

    @Benchmark
    public int quickselect() {
        return KthLargest.byQuickselect(numbers, k);
    }

    @Benchmark
    public int sorting() {
        return KthLargest.bySorting(numbers, k);
    }
}
