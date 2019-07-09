package io.github.dndanoff.core.business_case.common;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.dndanoff.core.business_case.BusinessCase;
import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.dao.WriteRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CreateBusinessCase <E extends Entity> implements BusinessCase<E>{
	
	protected final WriteRepository<E> writeRepo;
	protected final ReadRepository<E> readRepo;
	protected final Validator<E> validator;
	
	@Autowired
	public CreateBusinessCase(ReadRepository<E> readRep, WriteRepository<E> writeRepo, Validator<E> validator) {
		this.writeRepo = writeRepo;
		this.validator = validator;
		this.readRepo = readRep;
	}
	
	public E create(E entity) {
		log.debug("Calling create with arguments entity={}", entity);
		if (!validator.isModelValid(entity)) {
            log.debug("Skipping create, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
		if(readRepo.count(entity) >= 1) {
			log.debug("Skipping create, because entity with same data already present");
            throw new IllegalArgumentException("entity with same data already presen");
		}
		return writeRepo.create(entity);
	}
}
