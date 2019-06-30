package io.github.dndanoff.core.business_case;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.exception.EntityNotFoundException;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetEntitiesBusinessCase implements BusinessCase{
    private final ReadRepository<Entity> repo;
    private final Validator<ListInput> entityValidator;

    @Autowired
    public GetEntitiesBusinessCase(ReadRepository<Entity> repo, Validator<ListInput> entityValidator) {
        this.repo = repo;
        this.entityValidator = entityValidator;
    }

    public ResultList getAllEntities(ListInput listInput) {
        log.debug("Returning payload for getAllEntities with listInput={}",
        		listInput);
        if (!entityValidator.isModelValid(listInput)) {
            log.debug("Skipping getAllEntities, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
        return repo.findAll(listInput);
    }

    public Entity getAircraftTypeById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

}
