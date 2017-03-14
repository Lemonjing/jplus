package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A decorating {@code OutputStream} that counts the number of bytes that have
 * been written to its underlying stream.
 *
 * @author Osman KOCAK
 */
public final class CountingOutputStream extends OutputStream {
    private final OutputStream out;
    private final AtomicLong counter;

    /**
     * Creates a new {@code CountingOutputStream}.
     *
     * @param out the underlying stream.
     * @throws NullPointerException if {@code out} is {@code null}.
     */
    public CountingOutputStream(OutputStream out) {
        Parameters.checkNotNull(out);
        this.out = out;
        this.counter = new AtomicLong();
    }

    /**
     * Returns the number of bytes that have been written to the underlying
     * stream so far.
     *
     * @return the number of bytes that have been written so far.
     */
    public long getCount() {
        return counter.get();
    }

    /**
     * Sets the counter to 0 and returns its value before resetting it.
     *
     * @return the number of bytes that have been written so far.
     */
    public long resetCount() {
        return counter.getAndSet(0);
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void write(int i) throws IOException {
        out.write(i);
        counter.incrementAndGet();
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b);
        counter.addAndGet(b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        counter.addAndGet(Math.min(b.length - off, len));
    }
}
