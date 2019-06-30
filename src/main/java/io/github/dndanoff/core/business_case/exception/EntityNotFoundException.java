package io.github.dndanoff.core.business_case.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Integer id) {
	    super("Could not find AircraftType " + id);
	  }
}
