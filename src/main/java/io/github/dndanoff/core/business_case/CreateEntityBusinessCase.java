package io.github.dndanoff.core.business_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.dao.WriteRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateEntityBusinessCase<E extends Entity> implements BusinessCase<E>{
	
	private final WriteRepository<E> repo;
	private final Validator<E> entityValidator;
	
	@Autowired
	public CreateEntityBusinessCase(WriteRepository<E> repo, Validator<E> entityValidator) {
		this.repo = repo;
		this.entityValidator = entityValidator;
	}
	
	public void createEntity(E entity) {
		log.debug("Calling createEntity with arguments entity={}", entity);
		if (!entityValidator.isModelValid(entity)) {
            log.debug("Skipping getAllEntities, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
		repo.create(entity);
	}
}
