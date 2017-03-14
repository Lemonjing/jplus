package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.Reader;
import java.util.Arrays;

/**
 * A {@code Reader} whose source are {@link String}s. Instances of this class
 * behave exactly as {@link java.io.StringReader} instances except that they
 * never throw {@link java.io.IOException}s. Not thread-safe.
 *
 * @author Osman KOCAK
 */
public final class StringReader extends Reader {
    private int index;
    private int mark;
    private final StringBuilder in;

    /**
     * Creates a new {@code StringReader}.
     *
     * @param strings the input {@code String}s.
     * @throws NullPointerException if {@code strings} is {@code null} or if
     *                              it contains a {@code null} reference.
     */
    public StringReader(String... strings) {
        this(Arrays.asList(strings));
    }

    /**
     * Creates a new {@code StringReader}.
     *
     * @param strings the input {@link String}s.
     * @throws NullPointerException if {@code strings} is {@code null} or if
     *                              it contains a {@code null} reference.
     */
    public StringReader(Iterable<String> strings) {
        this.in = new StringBuilder();
        for (String str : strings) {
            Parameters.checkNotNull(str);
            this.in.append(str);
        }
        this.in.trimToSize();
    }

    @Override
    public boolean ready() {
        return true;
    }

    @Override
    public int read() {
        return finished() ? -1 : in.charAt(index++);
    }

    @Override
    public int read(char[] buf) {
        return read(buf, 0, buf.length);
    }

    @Override
    public int read(char[] buf, int off, int len) {
        if (off < 0 || len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        if (finished()) {
            return -1;
        }
        int n = 0;
        for (int i = off; i < off + len && index < in.length(); i++) {
            buf[i] = in.charAt(index++);
            n++;
        }
        return n;
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    /**
     * Marks the current position in the stream. Subsequent calls to reset()
     * will reposition the stream to this point.
     *
     * @param readLimit limit on the number of characters that may be read
     *                  while still preserving the mark. Because the stream's input
     *                  comes from {@code String}s, there is no actual limit, so this
     *                  argument must not be negative, but is otherwise ignored.
     * @throws IllegalArgumentException if {@code readLimit} is negative.
     */
    @Override
    public void mark(int readLimit) {
        Parameters.checkCondition(readLimit >= 0);
        mark = index;
    }

    /**
     * Resets the stream to the most recent mark, or to the beginning of the
     * stream if it has never been marked.
     */
    @Override
    public void reset() {
        index = mark;
    }

    @Override
    public long skip(long n) {
        Parameters.checkCondition(n >= 0);
        if (finished()) {
            return 0;
        }
        long skipped = n;
        if (n > in.length() - index) {
            skipped = in.length() - index;
        }
        index += skipped;
        return skipped;
    }

    @Override
    public void close() {
        /* ... */
    }

    private boolean finished() {
        return index >= in.length();
    }
}
