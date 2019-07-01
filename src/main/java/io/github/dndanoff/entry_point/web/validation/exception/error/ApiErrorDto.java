package io.github.dndanoff.entry_point.web.validation.exception.error;

import org.springframework.http.HttpStatus;

public class ApiErrorDto {
	private final HttpStatus status;
	private final String message;
	private final String errorInfo;
	private final ValidationErrorDto valdiationError;

	public ApiErrorDto(HttpStatus status, String message) {
		this(status, message, null, null);
	}

	public ApiErrorDto(HttpStatus status, String message, ValidationErrorDto valdiationError) {
		this(status, message, null, valdiationError);
	}

	public ApiErrorDto(HttpStatus status, String message, String error) {
		this(status, message, error, null);
	}

	public ApiErrorDto(HttpStatus status, String message, String errorInfo, ValidationErrorDto valdiationError) {
		super();
		this.status = status;
		this.message = message;
		this.errorInfo = errorInfo;
		this.valdiationError = valdiationError;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public ValidationErrorDto getValdiationError() {
		return valdiationError;
	}

}
