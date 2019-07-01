package io.github.dndanoff.entry_point.web.validation.exception;

public final class ApiBadRequestException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4016950916785573924L;

	public ApiBadRequestException() {
        super();
    }

    public ApiBadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApiBadRequestException(final String message) {
        super(message);
    }

    public ApiBadRequestException(final Throwable cause) {
        super(cause);
    }

}
