package io.github.dndanoff.core.business_case.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.BusinessCase;
import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import io.github.dndanoff.core.vo.SearchInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SearchBusinessCase <E extends Entity> implements BusinessCase<E>{

	protected final ReadRepository<E> repo;
	protected final Validator<SearchInput> validator;
	protected final Validator<ListInput> listValidator;

    @Autowired
    public SearchBusinessCase(ReadRepository<E> repo, Validator<SearchInput> validator, Validator<ListInput> listValidator) {
        this.repo = repo;
        this.validator = validator;
        this.listValidator = listValidator;
    }

    public ResultList<E> search(SearchInput searchInput, ListInput listInput) {
        log.debug("Returning payload for search with searchInput={} listInput={}",
        		searchInput, listInput);
        if (!validator.isModelValid(searchInput)) {
            log.debug("Skipping search, because of invalid search data");
            throw new IllegalArgumentException("invalid search data");
        }
        
        if (!listValidator.isModelValid(listInput)) {
            log.debug("Skipping search, because of invalid list data");
            throw new IllegalArgumentException("invalid list data");
        }
        return repo.search(listInput, searchInput);
    }

}
