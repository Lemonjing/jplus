package com.ryan.security;

/**
 * Key derivation function.
 *
 * @see KDFs
 *
 * @author Osman KOCAK
 */
public interface KDF
{
	/**
	 * Derives a key from the given secret and salt.
	 *
	 * @param secret the secret.
	 * @param salt the salt.
	 *
	 * @return the derived key.
	 *
	 * @throws NullPointerException if one of the arguments is {@code null}.
	 */
	byte[] deriveKey(byte[] secret, byte[] salt);
}
