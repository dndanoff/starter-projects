package io.github.dndanoff.core.business_case;


import org.springframework.beans.factory.annotation.Autowired;

import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.exception.EntityNotFoundException;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetEntitiesBusinessCase<E extends Entity> implements BusinessCase<E>{
    private final ReadRepository<E> repo;
    private final Validator<ListInput> entityValidator;

    @Autowired
    public GetEntitiesBusinessCase(ReadRepository<E> repo, Validator<ListInput> entityValidator) {
        this.repo = repo;
        this.entityValidator = entityValidator;
    }

    public ResultList<E> getAllEntities(ListInput listInput) {
        log.debug("Returning payload for getAllEntities with listInput={}",
        		listInput);
        if (!entityValidator.isModelValid(listInput)) {
            log.debug("Skipping getAllEntities, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
        return repo.findAll(listInput);
    }

    public E getEntityById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

}
