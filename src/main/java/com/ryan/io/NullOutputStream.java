package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.OutputStream;

/**
 * {@code OutputStream} that simply discards all data written to it (similar to
 * /dev/null on Unix systems). Instances of this class are immutable.
 *
 * @author Osman KOCAK
 */
public final class NullOutputStream extends OutputStream {
    @Override
    public void close() {
        /* ... */
    }

    @Override
    public void flush() {
		/* ... */
    }

    @Override
    public void write(byte[] b) {
        Parameters.checkNotNull(b);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        if (off < 0 || len < 0 || off + len > b.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void write(int i) {
		/* ... */
    }
}
