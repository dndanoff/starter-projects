package io.github.dndanoff.entry_point.web.validation.exception.error;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDto {
	
	private final List<FieldErrorDto> fieldErrors = new ArrayList<>();

	public ValidationErrorDto() {
		super();
	}

	public final void addFieldError(final String path, final String message) {
		final FieldErrorDto error = new FieldErrorDto(path, message);
		fieldErrors.add(error);
	}

	public final List<FieldErrorDto> getFieldErrors() {
		return fieldErrors;
	}

	@Override
	public final String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ValidationErrorDTO [fieldErrors=").append(fieldErrors).append("]");
		return builder.toString();
	}
}
