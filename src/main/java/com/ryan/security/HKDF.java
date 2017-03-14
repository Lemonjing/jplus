package com.ryan.security;

import com.ryan.util.*;

/**
 * HMAC-based Key Derivation Function (RFC 5869). Instances of this class are
 * immutable.
 *
 * @author Osman KOCAK
 */
final class HKDF implements KDF {
    private final int dkLen;
    private final byte[] info;
    private final Algorithm<MAC> algorithm;

    /**
     * Creates a new {@code HKDF} instance.
     *
     * @param algorithm the MAC algorithm to use.
     * @param info      optional context and application specific information,
     *                  may be {@code null} or empty.
     * @param dkLen     the desired length for derived keys, in bytes.
     * @throws NullPointerException     if {@code algorithm} is {@code null}.
     * @throws IllegalArgumentException if {@code dkLen} is negative, or if
     *                                  the MAC algorithm is unknown, or if {@code dkLen} is greater
     *                                  than 255 * MAC algorithm's output length.
     */
    HKDF(Algorithm<MAC> algorithm, byte[] info, int dkLen) {
        Parameters.checkCondition(dkLen > 0);
        MAC mac = Factory.newMAC(algorithm, new byte[0]);
        Parameters.checkCondition(dkLen <= 255 * mac.length());
        this.algorithm = algorithm;
        this.dkLen = dkLen;
        this.info = info == null ? new byte[0] : XArrays.copyOf(info);
    }

    @Override
    public byte[] deriveKey(byte[] secret, byte[] salt) {
        return expand(extract(secret, salt));
    }

    private byte[] extract(byte[] key, byte[] salt) {
        return Factory.newMAC(algorithm, salt).digest(key);
    }

    private byte[] expand(byte[] key) {
        MAC mac = Factory.newMAC(algorithm, key);
        ByteBuffer t = new ByteBuffer(dkLen + mac.length());
        int n = (int) Math.ceil((double) dkLen / mac.length());
        byte[] u = new byte[0];
        for (int i = 1; i <= n; i++) {
            u = mac.update(u).update(info).digest((byte) i);
            t.append(u);
        }
        return t.toByteArray(0, dkLen);
    }

    @Override
    public String toString() {
        return XObjects.toStringBuilder("HKDF").append("MAC", algorithm)
                .append("info", "0x" + BaseEncoding.BASE_16.encode(info))
                .append("dkLen", dkLen).toString();
    }
}
