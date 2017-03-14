package com.ryan.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * Common I/O utilities.
 *
 * @author Osman KOCAK
 */
public final class IO {
    /**
     * Silently closes the given {@code Closeable}.
     *
     * @param stream the stream to close, may be {@code null}.
     */
    public static void close(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException ex) {
                /* Ignored... */
            }
        }
    }

    /**
     * Silently flushes the given {@code Flushable}.
     *
     * @param stream the stream to flush, may be {@code null}.
     */
    public static void flush(Flushable stream) {
        if (stream != null) {
            try {
                stream.flush();
            } catch (IOException ex) {
				/* Ignored... */
            }
        }
    }

    private IO() {
		/* ... */
    }
}
