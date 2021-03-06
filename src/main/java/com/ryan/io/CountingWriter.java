package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A decorating {@code Writer} that counts the number of characters that have
 * been written to its underlying {@link Writer}.
 *
 * @author Osman KOCAK
 */
public final class CountingWriter extends Writer {
    private final Writer out;
    private final AtomicLong counter;

    /**
     * Creates a new {@code CountingWriter}.
     *
     * @param out the underlying {@code Writer}.
     * @throws NullPointerException if {@code out} is {@code null}.
     */
    public CountingWriter(Writer out) {
        Parameters.checkNotNull(out);
        this.out = out;
        this.counter = new AtomicLong();
    }

    /**
     * Returns the number of characters that have been written to the
     * underlying stream so far.
     *
     * @return the number of characters that have been written so far.
     */
    public long getCount() {
        return counter.get();
    }

    /**
     * Sets the counter to 0 and returns its value before resetting it.
     *
     * @return the number of characters that have been written so far.
     */
    public long resetCount() {
        return counter.getAndSet(0);
    }

    @Override
    public Writer append(char c) throws IOException {
        out.append(c);
        counter.incrementAndGet();
        return this;
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        CharSequence sequence = csq == null ? "null" : csq;
        out.append(sequence);
        counter.addAndGet(sequence.length());
        return this;
    }

    @Override
    public Writer append(CharSequence csq, int start, int end)
            throws IOException {
        CharSequence sequence = csq == null ? "null" : csq;
        out.append(sequence.subSequence(start, end));
        counter.addAndGet(sequence.subSequence(start, end).length());
        return this;
    }

    @Override
    public void write(int c) throws IOException {
        out.write(c);
        counter.incrementAndGet();
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        out.write(cbuf);
        counter.addAndGet(cbuf.length);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf, off, len);
        counter.addAndGet(Math.min(cbuf.length - off, len));
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str);
        counter.addAndGet(str.length());
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str, off, len);
        counter.addAndGet(Math.min(str.length() - off, len));
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
