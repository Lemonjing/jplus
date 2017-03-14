package com.ryan.util;

/**
 * This exception is meant to be thrown in situations that cannot happen in
 * practice. Use with parcimony.
 *
 * @author Osman KOCAK
 */
public final class CannotHappenException extends RuntimeException {
    private static final long serialVersionUID = 3651678489121314654L;

    /**
     * Creates a new {@code CannotHappenException}.
     */
    public CannotHappenException() {
        /* ... */
    }

    /**
     * Creates a new {@code CannotHappenException}.
     *
     * @param message the error message.
     */
    public CannotHappenException(String message) {
        super(message);
    }

    /**
     * Creates a new {@code CannotHappenException}.
     *
     * @param cause the error that caused this one.
     */
    public CannotHappenException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new {@code CannotHappenException}.
     *
     * @param message the error message.
     * @param cause   the error that caused this one.
     */
    public CannotHappenException(String message, Throwable cause) {
        super(message, cause);
    }
}
