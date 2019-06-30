package io.github.dndanoff.entry_point.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonView;

import io.github.dndanoff.entry_point.web.dto.DtoView;
import io.github.dndanoff.entry_point.web.dto.DtoWithId;
import io.github.dndanoff.entry_point.web.dto.ListInputDto;
import io.github.dndanoff.entry_point.web.dto.ResultListDto;
import io.github.dndanoff.entry_point.web.facade.Facade;
import io.github.dndanoff.entry_point.web.validation.RestPrecondition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@CrossOrigin
public abstract class AbstractReadOnlyController <D extends DtoWithId> {

    protected final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AbstractReadOnlyController(ApplicationEventPublisher eventPublisher) {
        super();
        this.eventPublisher = eventPublisher;
    }
    
    @GetMapping(value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long countResources() {
    	log.debug("Received request to countResources() resources");
        return countResourcesInternal();
    }
    
    protected abstract long countResourcesInternal();
    
    @JsonView(DtoView.Aggregated.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	public ResultListDto<D> findResourcesPaginated(
			@RequestParam(required = false) ListInputDto listInput) {
    	log.debug("Received request to findResourcesPaginated() with params listInput={}", listInput);
		return findResourcesPaginatedInternal(listInput);
	}
	
    protected abstract ResultListDto<D> findResourcesPaginatedInternal(ListInputDto listInput) ;
    
    @JsonView(DtoView.Detailed.class)
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public D findOneResource(@PathVariable final Long id) {
    	log.debug("Received request to findOneResource() with params id={}", id);
    	RestPrecondition.checkRequestElementNotNull(id);
    	return RestPrecondition.checkNotNull(findOneResourceInternal(id));
    }

    protected abstract D findOneResourceInternal(final Long id) ;
    
    protected abstract Facade<D> getServiceFacade();
}
