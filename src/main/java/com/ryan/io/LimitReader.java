package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.IOException;
import java.io.Reader;

/**
 * A decorating {@code Reader} that will only supply characters up to a certain
 * length. Thread safe.
 *
 * @author Osman KOCAK
 */
final class LimitReader extends Reader {
    private final Reader reader;
    private final long limit;
    private final Object innerLock;

    private long position;
    private long mark;
    private long markLimit;

    /**
     * Creates a new {@code LimitReader}.
     *
     * @param reader the underlying reader.
     * @param limit  the maximum number of characters to provide from {@code reader}.
     * @throws NullPointerException     if {@code reader} is {@code null}.
     * @throws IllegalArgumentException if {@code limit} is negative.
     */
    LimitReader(Reader reader, long limit) {
        Parameters.checkNotNull(reader);
        Parameters.checkCondition(limit >= 0);
        this.reader = reader;
        this.limit = limit;
        this.innerLock = new Object();
        this.position = 0L;
        this.mark = -1L;
    }

    @Override
    public boolean ready() throws IOException {
        synchronized (innerLock) {
            return reader.ready();
        }
    }

    @Override
    public int read() throws IOException {
        synchronized (innerLock) {
            if (position >= limit) {
                return -1;
            }
            int c = reader.read();
            shiftPosition(c != -1 ? 1 : -1);
            return c;
        }
    }

    @Override
    public int read(char[] buf) throws IOException {
        return read(buf, 0, buf.length);
    }

    @Override
    public int read(char[] buf, int off, int len) throws IOException {
        synchronized (innerLock) {
            if (position >= limit) {
                return -1;
            }
            int max = (int) Math.min(len, limit - position);
            int n = reader.read(buf, off, max);
            shiftPosition(n);
            return n;
        }
    }

    @Override
    public boolean markSupported() {
        synchronized (innerLock) {
            return reader.markSupported();
        }
    }

    @Override
    public void mark(int readLimit) throws IOException {
        synchronized (innerLock) {
            reader.mark(readLimit);
            mark = position;
            markLimit = position + readLimit;
        }
    }

    @Override
    public void reset() throws IOException {
        synchronized (innerLock) {
            if (mark == -1 || (mark > -1 && position > markLimit)) {
                throw new IOException("Missing mark");
            }
            reader.reset();
            position = mark;
        }
    }

    @Override
    public long skip(long n) throws IOException {
        synchronized (innerLock) {
            long skipped = reader.skip(Math.min(n, limit - position));
            shiftPosition(skipped);
            return skipped;
        }
    }

    @Override
    public void close() throws IOException {
        synchronized (innerLock) {
            reader.close();
        }
    }

    private void shiftPosition(long n) {
        if (n != -1) {
            position += n;
        }
    }
}
