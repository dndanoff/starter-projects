package io.github.dndanoff.core.business_case.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.BusinessCase;
import io.github.dndanoff.core.business_case.dao.WriteRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DeleteBusinessCase <E extends Entity> implements BusinessCase<E>{
	
	protected final WriteRepository<E> repo;

    @Autowired
    public DeleteBusinessCase(WriteRepository<E> repo) {
        this.repo = repo;
    }

    public void delete(Integer id) {
    	log.debug("Calling delete with arguments id={}", id);
		if (id == null) {
            log.debug("Skipping delete, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
		repo.delete(id);
    }
}
