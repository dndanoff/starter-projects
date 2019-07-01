package io.github.dndanoff.entry_point.web;

import java.nio.file.AccessDeniedException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.dndanoff.core.business_case.exception.EntityNotFoundException;
import io.github.dndanoff.entry_point.web.validation.exception.ApiBadRequestException;
import io.github.dndanoff.entry_point.web.validation.exception.ApiConflictException;
import io.github.dndanoff.entry_point.web.validation.exception.ApiForbiddenException;
import io.github.dndanoff.entry_point.web.validation.exception.ApiResourceNotFoundException;
import io.github.dndanoff.entry_point.web.validation.exception.ApiValidationException;
import io.github.dndanoff.entry_point.web.validation.exception.error.ApiErrorDto;
import io.github.dndanoff.entry_point.web.validation.exception.error.ValidationErrorDto;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
	private static final String EXCEPTION_MESSAGE = "Bad Request: {}";

	// 400
	@Override
	protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final ValidationErrorDto validationError = new ValidationErrorDto();
		for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			final String localizedErrorMessage = fieldError.getDefaultMessage();
			validationError.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message, validationError);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class, ApiValidationException.class })
	public final ResponseEntity<Object> handleBadConstraintRequest(final ConstraintViolationException ex,
			final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final ValidationErrorDto validationError = new ValidationErrorDto();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			validationError.addFieldError(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath(),
					violation.getMessage());
		}

		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message, validationError);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	}

	@ExceptionHandler(value = { ApiBadRequestException.class })
	public final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	}

	// 403
	@ExceptionHandler({ AccessDeniedException.class, ApiForbiddenException.class })
	public ResponseEntity<Object> handleEverything(final AccessDeniedException ex, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		logger.error("403 Status Code", ex);
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.FORBIDDEN, message);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	}

	// 404
	@ExceptionHandler({ApiResourceNotFoundException.class, EntityNotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		log.warn("Not Found: {}", ex.getMessage());
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.NOT_FOUND, message);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	}

	// 409
	@ExceptionHandler({ ApiConflictException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		log.warn("Conflict: {}", ex.getMessage());
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.CONFLICT, message);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	}

	// 405
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.METHOD_NOT_ALLOWED, message, builder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 415
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message,
				builder.substring(0, builder.length() - 2));
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 500

	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Object> handle500s(final RuntimeException ex, final WebRequest request) {
		log.error("500 Status Code", ex);
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, message);

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		log.error("error", ex);
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, message);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {

		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final ValidationErrorDto validationError = new ValidationErrorDto();
		for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			final String localizedErrorMessage = fieldError.getDefaultMessage();
			validationError.addFieldError(fieldError.getField(), localizedErrorMessage);
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			validationError.addFieldError(error.getObjectName(), error.getDefaultMessage());
		}

		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();

		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message, validationError);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String error = ex.getRequestPartName() + " part is missing";
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String error = ex.getParameterName() + " parameter is missing";
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
			final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		log.info(EXCEPTION_MESSAGE, ex.getLocalizedMessage());
		log.debug(EXCEPTION_MESSAGE, ex);
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.NOT_FOUND, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
