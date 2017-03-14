package com.ryan.io;

import com.ryan.util.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An {@code InputStream} which reads sequentially from multiple sources. Mark
 * and reset are not supported. Not thread safe.
 *
 * @author Osman KOCAK
 */
final class ConcatInputStream extends InputStream
{
	private int index;
	private final List<InputStream> streams;

	/**
	 * Creates a new {@code ConcatInputStream}.
	 *
	 * @param streams the streams to concatenate.
	 *
	 * @throws NullPointerException if {@code streams} is {@code null} or
	 *	if it contains a {@code null} reference.
	 * @throws IllegalArgumentException if {@code streams} is empty.
	 */
	ConcatInputStream(InputStream... streams)
	{
		this(Arrays.asList(streams));
	}

	/**
	 * Creates a new {@code ConcatInputStream}.
	 *
	 * @param streams the streams to concatenate.
	 *
	 * @throws NullPointerException if {@code streams} is {@code null} or
	 *	if it contains a {@code null} reference.
	 * @throws IllegalArgumentException if {@code streams} is empty.
	 */
	ConcatInputStream(Iterable<? extends InputStream> streams)
	{
		this.streams = new ArrayList<InputStream>();
		for (InputStream stream : streams) {
			this.streams.add(Parameters.checkNotNull(stream));
		}
		Parameters.checkCondition(!this.streams.isEmpty());
	}

	@Override
	public int available() throws IOException
	{
		return finished() ? 0 : current().available();
	}

	@Override
	public void close()
	{
		for (InputStream stream : streams) {
			IO.close(stream);
		}
	}

	@Override
	public int read() throws IOException
	{
		int b = finished() ? -1 : current().read();
		return b != -1 ? b : next() ? read() : -1;
	}

	@Override
	public int read(byte[] b) throws IOException
	{
		int n = finished() ? -1 : current().read(b);
		return n != -1 ? n : next() ? read(b) : -1;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException
	{
		int n = finished() ? -1 : current().read(b, off, len);
		return n != -1 ? n : next() ? read(b, off, len) : -1;
	}

	@Override
	public long skip(long n) throws IOException
	{
		return finished() ? 0L : current().skip(n);
	}

	private InputStream current()
	{
		return streams.get(index);
	}

	private boolean finished()
	{
		return index >= streams.size();
	}

	private boolean next()
	{
		index++;
		return !finished();
	}
}
