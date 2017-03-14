package com.ryan.net;

/**
 * Thrown to indicate that a {@code String} could not be parsed to a {@code URN}
 * reference.
 *
 * @author Osman KOCAK
 * @see URN
 */
public final class URNSyntaxException extends Exception {
    private static final long serialVersionUID = 106807221764107977L;

    /**
     * Creates a new {@code URNSyntaxException}.
     *
     * @param message the error message.
     */
    public URNSyntaxException(String message) {
        super(message);
    }

    /**
     * Creates a new {@code URNSyntaxException}.
     *
     * @param cause the error that caused this one.
     */
    public URNSyntaxException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new {@code URNSyntaxException}.
     *
     * @param message the error message.
     * @param cause   the error that caused this one.
     */
    public URNSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
}
