package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.Writer;

/**
 * {@code Writer} that simply discards all characters written to it (similar to
 * /dev/null on Unix systems). Instances of this class are immutable.
 *
 * @author Osman KOCAK
 */
public final class NullWriter extends Writer {
    @Override
    public NullWriter append(char c) {
        return this;
    }

    @Override
    public NullWriter append(CharSequence sequence) {
        Parameters.checkNotNull(sequence);
        return this;
    }

    @Override
    public NullWriter append(CharSequence sequence, int start, int end) {
        return append(sequence.subSequence(start, end));
    }

    @Override
    public void write(int c) {
        /* ... */
    }

    @Override
    public void write(char[] buf) {
        Parameters.checkNotNull(buf);
    }

    @Override
    public void write(char[] buf, int off, int len) {
        if (off < 0 || len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void write(String str) {
        Parameters.checkNotNull(str);
    }

    @Override
    public void write(String str, int off, int len) {
        if (off < 0 || len < 0 || off + len > str.length()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void flush() {
		/* ... */
    }

    @Override
    public void close() {
		/* ... */
    }
}
