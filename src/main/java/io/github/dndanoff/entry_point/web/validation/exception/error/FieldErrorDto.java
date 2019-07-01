package io.github.dndanoff.entry_point.web.validation.exception.error;

public class FieldErrorDto {
	
	private final String field;
	private final String message;

	public FieldErrorDto(final String field, final String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
