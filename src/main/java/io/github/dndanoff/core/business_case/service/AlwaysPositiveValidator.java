package io.github.dndanoff.core.business_case.service;

import io.github.dndanoff.core.Model;

public class AlwaysPositiveValidator<E extends Model> implements Validator<E>{

	@Override
	public boolean isModelValid(E entity) {
		return true;
	}

}
