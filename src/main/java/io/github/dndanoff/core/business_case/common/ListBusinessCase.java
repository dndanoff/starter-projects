package io.github.dndanoff.core.business_case.common;


import java.util.Optional;

import io.github.dndanoff.core.business_case.BusinessCase;
import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ListBusinessCase <E extends Entity> implements BusinessCase<E>{
	
    protected final ReadRepository<E> repo;
    protected final Validator<ListInput> validator;

    public ListBusinessCase(ReadRepository<E> repo, Validator<ListInput> validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public ResultList<E> getAll(ListInput listInput) {
        log.debug("Returning payload for getAllEntities with listInput={}",
        		listInput);
        if (!validator.isModelValid(listInput)) {
            log.debug("Skipping getAll, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
        return repo.findAll(listInput);
    }

    public Optional<E> getById(Integer id) {
        return repo.findById(id);
    }

}
