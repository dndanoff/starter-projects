package io.github.dndanoff.entry_point.web.validation.exception;

public final class ApiResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6505720804872338761L;

	public ApiResourceNotFoundException() {
        super();
    }

    public ApiResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApiResourceNotFoundException(final String message) {
        super(message);
    }

    public ApiResourceNotFoundException(final Throwable cause) {
        super(cause);
    }

}
