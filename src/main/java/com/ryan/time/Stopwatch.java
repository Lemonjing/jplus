package com.ryan.time;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Utility class to measure elapsed time between specific instants. Note that
 * while this class provides millisecond precision, it does not necessarily
 * provide millisecond accuracy (depends, among many things, on the accuracy of
 * the underlying system clock). Instances of this class are not thread-safe.
 *
 * @author Osman KOCAK
 */
public final class Stopwatch {
    private long referenceTime;
    private long elapsedTime;
    private boolean running;

    /**
     * Creates a new (unstarted) {@code Stopwatch}.
     */
    public Stopwatch() {
        this.referenceTime = now();
        this.elapsedTime = 0L;
        this.running = false;
    }

    /**
     * Returns {@code true} if {@link #start()} has been called on this
     * object, and {@link #stop()} hasn't been called since the last call
     * to {@code start()}.
     *
     * @return whether this stopwatch is running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Starts this stopwatch.
     *
     * @return this object.
     * @throws IllegalStateException if this stopwatch is already running.
     */
    public Stopwatch start() {
        if (running) {
            throw new IllegalStateException("Already running...");
        }
        referenceTime = now();
        running = true;
        return this;
    }

    /**
     * Stops this stopwatch.
     *
     * @return this object.
     * @throws IllegalStateException if this stopwatch is already stopped.
     */
    public Stopwatch stop() {
        if (!running) {
            throw new IllegalStateException("Already idle...");
        }
        elapsedTime += now() - referenceTime;
        running = false;
        return this;
    }

    /**
     * Resets this stopwatch (sets the elapsed time for this stopwatch to
     * zero). Calling this method does NOT modify this stopwatch's state.
     *
     * @return this object.
     */
    public Stopwatch reset() {
        elapsedTime = 0L;
        referenceTime = now();
        return this;
    }

    /**
     * Returns the current elapsed time, expressed in milliseconds. Calling
     * this method does NOT modify this stopwatch's state.
     *
     * @return the current elapsed time.
     */
    public long elapsedTime() {
        if (running) {
            return (elapsedTime + now() - referenceTime) / 1000000L;
        }
        return elapsedTime / 1000000L;
    }

    @Override
    public String toString() {
        return Duration.of(elapsedTime(), MILLISECONDS).toString();
    }

    private long now() {
        return System.nanoTime();
    }
}
