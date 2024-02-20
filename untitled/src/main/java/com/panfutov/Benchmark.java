package com.panfutov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
//@Measurement(iterations = 2, time = 10, timeUnit = TimeUnit.NANOSECONDS)
//@Warmup(iterations = 2, time = 10, timeUnit = TimeUnit.NANOSECONDS)
public class Benchmark {

    private final Service service = new Service();

    @org.openjdk.jmh.annotations.Benchmark
    public void exceptions(Blackhole blackhole) {
        try {
            service.doWithException();
        } catch (Service.BusinessException e) {
            blackhole.consume(e.getMessage());
            // ignore
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void jresult(Blackhole blackhole) {
            service.doWithResult()
                .ifFailure(failure -> blackhole.consume(failure.firstError().getMessage()));
    }
    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(new String[]{ "-prof", "async:libPath=/Users/artyom/Downloads/async-profiler-3.0-macos/lib/libasyncProfiler.dylib"});
    }
}
