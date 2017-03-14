package com.ryan.security;

import com.ryan.util.Parameters;
import com.ryan.util.XObjects;

import java.util.Arrays;

/**
 * PBKDF1 Key Derivation Function (RFC 2898). Instances of this class are
 * immutable.
 *
 * @author Osman KOCAK
 */
final class PBKDF1 implements KDF {
    private final int dkLen;
    private final int iterationCount;
    private final Algorithm<Digest> algorithm;

    /**
     * Creates a new {@code PBKDF1} instance.
     *
     * @param algorithm      the digest algorithm to use.
     * @param iterationCount the desired number of iterations.
     * @param dkLen          the desired length for derived keys, in bytes.
     * @throws NullPointerException     if {@code algorithm} is {@code null}.
     * @throws IllegalArgumentException if {@code iterationCount} or
     *                                  {@code dkLen} is negative, or if {@code dkLen} is greater than
     *                                  the digest algorithm's output length, or if the digest algorithm
     *                                  is unknown.
     */
    PBKDF1(Algorithm<Digest> algorithm, int iterationCount, int dkLen) {
        Parameters.checkCondition(dkLen > 0);
        Parameters.checkCondition(iterationCount > 0);
        Digest digest = Factory.newDigest(algorithm);
        Parameters.checkCondition(dkLen <= digest.length());
        this.algorithm = algorithm;
        this.iterationCount = iterationCount;
        this.dkLen = dkLen;
    }

    @Override
    public byte[] deriveKey(byte[] secret, byte[] salt) {
        Digest digest = Factory.newDigest(algorithm);
        byte[] hash = digest.update(secret).digest(salt);
        for (int i = 1; i < iterationCount; i++) {
            hash = digest.digest(hash);
        }
        return Arrays.copyOf(hash, dkLen);
    }

    @Override
    public String toString() {
        return XObjects.toStringBuilder("PBKDF1")
                .append("digest", algorithm)
                .append("iterationCount", iterationCount)
                .append("dkLen", dkLen).toString();
    }
}
