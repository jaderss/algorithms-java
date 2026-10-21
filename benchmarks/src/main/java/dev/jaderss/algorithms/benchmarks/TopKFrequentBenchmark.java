package dev.jaderss.algorithms.benchmarks;

import dev.jaderss.algorithms.hashing.TopKFrequent;
import java.util.List;
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
public class TopKFrequentBenchmark {

    @Param({"10000", "1000000"})
    int size;

    @Param({"100", "100000"})
    int distinct;

    @Param({"10"})
    int k;

    int[] numbers;

    @Setup
    public void generate() {
        SplittableRandom random = new SplittableRandom(42);
        numbers = random.ints(size, 0, distinct).toArray();
    }

    @Benchmark
    public List<Integer> buckets() {
        return TopKFrequent.byBuckets(numbers, k);
    }

    @Benchmark
    public List<Integer> heap() {
        return TopKFrequent.byHeap(numbers, k);
    }

    @Benchmark
    public List<Integer> sorting() {
        return TopKFrequent.bySorting(numbers, k);
    }
}
