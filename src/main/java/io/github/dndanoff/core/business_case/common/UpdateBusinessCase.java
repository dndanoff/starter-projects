package io.github.dndanoff.core.business_case.common;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.dndanoff.core.business_case.BusinessCase;
import io.github.dndanoff.core.business_case.dao.WriteRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class UpdateBusinessCase <E extends Entity> implements BusinessCase<E>{
	
	protected final WriteRepository<E> repo;
	protected final Validator<E> validator;

    @Autowired
    public UpdateBusinessCase(WriteRepository<E> repo, Validator<E> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public void update(Integer id, E entity) {
    	log.debug("Calling update with arguments entity={}", entity);
		if (!validator.isModelValid(entity)) {
            log.debug("Skipping update, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
		repo.update(entity);
    }
}
