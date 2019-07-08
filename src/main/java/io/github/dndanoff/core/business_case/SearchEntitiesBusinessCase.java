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
public class SearchEntitiesBusinessCase<E extends Entity> implements BusinessCase<E>{

    private final ReadRepository<E> repo;
    private final Validator<SearchInput> searchValidator;
    private final Validator<ListInput> listValidator;

    @Autowired
    public SearchEntitiesBusinessCase(ReadRepository<E> repo, Validator<SearchInput> searchValidator, Validator<ListInput> listValidator) {
        this.repo = repo;
        this.searchValidator = searchValidator;
        this.listValidator = listValidator;
    }

    public ResultList<E> getSearchEntities(SearchInput searchInput, ListInput listInput) {
        log.debug("Returning payload for getSearchEntities with searchInput={}",
        		searchInput);
        if (!searchValidator.isModelValid(searchInput)) {
            log.debug("Skipping getSearchEntities, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
        if (!listValidator.isModelValid(listInput)) {
            log.debug("Skipping getSearchEntities, because of invalid data");
            throw new IllegalArgumentException("invalid data");
        }
        return repo.search(listInput, searchInput);
    }

}
