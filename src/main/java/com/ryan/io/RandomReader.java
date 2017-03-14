package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.Reader;
import java.util.Arrays;
import java.util.Random;

/**
 * {@code Reader} that (pseudo) randomly returns characters from a specified
 * alphabet. This stream has no end of file. Closing a {@code RandomReader} has
 * no effect. Never throws {@code IOException}s. Thread safe.
 *
 * @author Osman KOCAK
 */
public final class RandomReader extends Reader {
    private static final Random PRNG = new Random();

    private final Random prng;
    private final char[] alphabet;

    /**
     * Creates a new {@code RandomReader}.
     *
     * @param alphabet the source alphabet to use.
     * @throws NullPointerException     if {@code alphabet} is {@code null}.
     * @throws IllegalArgumentException if {@code alphabet} is empty.
     */
    public RandomReader(char... alphabet) {
        this(PRNG, alphabet);
    }

    /**
     * Creates a new {@code RandomReader}.
     *
     * @param prng     the source of randomness to use.
     * @param alphabet the source alphabet to use.
     * @throws NullPointerException     if one of the arguments is {@code null}.
     * @throws IllegalArgumentException if {@code alphabet} is empty.
     */
    public RandomReader(Random prng, char... alphabet) {
        Parameters.checkNotNull(prng);
        Parameters.checkCondition(alphabet.length > 0);
        this.prng = prng;
        this.alphabet = Arrays.copyOf(alphabet, alphabet.length);
    }

    @Override
    public int read() {
        return alphabet[prng.nextInt(alphabet.length)];
    }

    @Override
    public int read(char[] cbuf) {
        for (int i = 0; i < cbuf.length; i++) {
            cbuf[i] = (char) read();
        }
        return cbuf.length;
    }

    @Override
    public int read(char[] cbuf, int off, int len) {
        if (off < 0 || len < 0 || off + len > cbuf.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < len; i++) {
            cbuf[off + i] = (char) read();
        }
        return len;
    }

    @Override
    public void close() {
        /* ... */
    }
}
