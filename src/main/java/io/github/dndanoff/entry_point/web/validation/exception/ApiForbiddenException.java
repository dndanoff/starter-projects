package io.github.dndanoff.entry_point.web.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when user is forbidden to execute specified operation or access specified data.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ApiForbiddenException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5043365085322261409L;

	public ApiForbiddenException() {
        super();
    }

    public ApiForbiddenException(final String message) {
        super(message);
    }

    public ApiForbiddenException(final Throwable cause) {
        super(cause);
    }

}