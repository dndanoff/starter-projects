package io.github.dndanoff.core.business_case;

import org.springframework.stereotype.Service;

import io.github.dndanoff.core.Model;
import io.github.dndanoff.core.business_case.service.Validator;

@Service
public class AlwaysPositiveValidator<E extends Model> implements Validator<E>{

	@Override
	public boolean isModelValid(E entity) {
		return true;
	}

}
