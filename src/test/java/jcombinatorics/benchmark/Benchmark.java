/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.benchmark;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A rudimentary class for timing benchmarks.
 *
 * @author Alistair A. Israel
 */
public class Benchmark {

    private final String name;

    private final Map<String, Result> results = new LinkedHashMap<String, Result>();

    private long totalNanos;

    /**
     * Default constructor.
     */
    public Benchmark() {
        this.name = "Benchmark@" + System.identityHashCode(results);
    }

    /**
     * @param name
     *        the benchmark suite's name
     */
    public Benchmark(final String name) {
        super();
        this.name = name;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @return the totalNanos
     */
    public final long getTotalNanos() {
        return totalNanos;
    }

    /**
     * @return the results
     */
    public final Collection<Result> getResults() {
        return results.values();
    }

    /**
     * @param taskName
     *        the task result name to get
     * @return the Result for the named task
     * @see java.util.Map#get(java.lang.Object)
     */
    public final Result get(final String taskName) {
        return results.get(taskName);
    }

    /**
     * @param taskName
     *        the task name
     * @param task
     *        the {@link Runnable} to time
     * @return the benchmark Result of running the task
     */
    public final Result bench(final String taskName, final Runnable task) {
        final Result result = single(taskName, task);
        totalNanos += result.getNanos();
        results.put(taskName, result);
        return result;
    }

    /**
     * @param taskName
     *        the task name
     * @param task
     *        a {@link Runnable}
     * @return {@link Result}
     */
    public static Result single(final String taskName, final Runnable task) {
        final long start = System.nanoTime();
        task.run();
        final long stop = System.nanoTime();
        return new Result(taskName, stop - start);
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class Result {

        private final String name;

        private final long nanos;

        /**
         * @param name
         *        the benchmark result name
         * @param nanos
         *        the number of nanoseconds the benchmark took
         */
        public Result(final String name, final long nanos) {
            super();
            this.name = name;
            this.nanos = nanos;
        }

        /**
         * @return the name
         */
        public final String getName() {
            return name;
        }

        /**
         * @return the millis
         */
        public final long getNanos() {
            return nanos;
        }

    }
}
