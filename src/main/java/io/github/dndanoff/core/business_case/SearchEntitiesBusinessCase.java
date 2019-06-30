package io.github.dndanoff.core.business_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import io.github.dndanoff.core.vo.SearchInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchEntitiesBusinessCase implements BusinessCase{

    private final ReadRepository<Entity> repo;
    private final Validator<SearchInput> entityValidator;

    @Autowired
    public SearchEntitiesBusinessCase(ReadRepository<Entity> repo, Validator<SearchInput> entityValidator) {
        this.repo = repo;
        this.entityValidator = entityValidator;
    }

    public ResultList getSearchEntities(SearchInput searchInput, ListInput listInput) {
        log.debug("Returning payload for getSearchEntities with searchInput={}",
        		searchInput);
        if (!entityValidator.isModelValid(searchInput)) {
            log.debug("Skipping getSearchEntities, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
        return repo.search(listInput, searchInput);
    }

}
