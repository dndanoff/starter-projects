package io.github.dndanoff.entry_point.web.validation.exception;

/**
 * Thrown when validation conflict error is found. Message used to describe the validation error.
 */
public class ApiValidationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2923744634250856612L;

	public ApiValidationException(final String message) {
        super(message);
    }

}
