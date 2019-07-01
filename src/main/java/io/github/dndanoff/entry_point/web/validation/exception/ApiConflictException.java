package io.github.dndanoff.entry_point.web.validation.exception;

public final class ApiConflictException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7390158891420056061L;

	public ApiConflictException() {
        super();
    }

    public ApiConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApiConflictException(final String message) {
        super(message);
    }

    public ApiConflictException(final Throwable cause) {
        super(cause);
    }

}
