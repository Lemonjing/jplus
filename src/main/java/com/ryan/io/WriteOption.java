package com.ryan.io;

/**
 * File write options.
 *
 * @author Osman KOCAK
 * @see XFiles
 * @see TextFiles
 */
public enum WriteOption {
    /**
     * Create a new file, failing if the file already exists. Can't be used
     * together with {@link #UPDATE}, {@link #OVERWRITE} and {@link #APPEND}.
     */
    CREATE,

    /**
     * Update an existing file, failing if the file doesn't exist. Can't be
     * used together with {@link #CREATE}.
     */
    UPDATE,

    /**
     * Overwrite the file (without first truncating it). Can't be used
     * together with {@link #CREATE} and {@link #APPEND}.
     */
    OVERWRITE,

    /**
     * Append data at the end of the file. Can't be used together with
     * {@link #CREATE} and {@link #OVERWRITE}.
     */
    APPEND;
}
