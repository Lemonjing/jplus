package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A decorating {@code OutputStream} that writes all bytes written to it to its
 * underlying streams. Named after the Unix 'tee' command. Not thread-safe.
 *
 * @author Osman KOCAK
 */
final class TeeOutputStream extends OutputStream {
    private final List<OutputStream> streams;

    /**
     * Creates a new {@code TeeOutputStream}.
     *
     * @param streams the streams to write to.
     * @throws NullPointerException if {@code streams} is {@code null} or
     *                              if it contains a {@code null} reference.
     */
    TeeOutputStream(OutputStream... streams) {
        this(Arrays.asList(streams));
    }

    /**
     * Creates a new {@code TeeOutputStream}.
     *
     * @param streams the streams to write to.
     * @throws NullPointerException if {@code streams} is {@code null} or
     *                              if it returns a {@code null} reference.
     */
    TeeOutputStream(Iterable<? extends OutputStream> streams) {
        this.streams = new ArrayList<OutputStream>();
        for (OutputStream stream : streams) {
            Parameters.checkNotNull(stream);
            this.streams.add(stream);
        }
    }

    @Override
    public void close() {
        for (OutputStream out : streams) {
            IO.close(out);
        }
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream out : streams) {
            out.flush();
        }
    }

    @Override
    public void write(int i) throws IOException {
        for (OutputStream out : streams) {
            out.write(i);
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (OutputStream out : streams) {
            out.write(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (OutputStream out : streams) {
            out.write(b, off, len);
        }
    }
}
