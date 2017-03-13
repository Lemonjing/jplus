package com.ryan.charset;

import java.nio.charset.Charset;

/**
 * Charsets guaranteed to be supported by all Java platform implementations.
 *
 * @author Osman KOCAK
 */
public final class Charsets
{
	/**
	 * Default charset of this Java virtual machine. The default charset is
	 * determined during virtual-machine startup and typically depends upon
	 * the locale and charset of the underlying operating system.
	 */
	public static final Charset DEFAULT = Charset.defaultCharset();

	/** 7-bit ASCII (ISO646-US). */
	public static final Charset US_ASCII = Charset.forName("US-ASCII");

	/** ISO Latin Alphabet 1 (ISO-LATIN-1). */
	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

	/** 8-bit UCS Transformation Format. */
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * 16-bit UCS Transformation Format, byte order identified by an
	 * optional byte-order mark.
	 */
	public static final Charset UTF_16 = Charset.forName("UTF-16");

	/** 16-bit UCS Transformation Format, big-endian byte order. */
	public static final Charset UTF_16BE = Charset.forName("UTF-16BE");

	/** 16-bit UCS Transformation Format, little-endian byte order. */
	public static final Charset UTF_16LE = Charset.forName("UTF-16LE");

	private Charsets()
	{
		/* ... */
	}
}
