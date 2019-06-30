package io.github.dndanoff.entry_point.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.dndanoff.entry_point.web.dto.DtoWithId;
import io.github.dndanoff.entry_point.web.validation.RestPrecondition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@CrossOrigin
public abstract class AbstractController <D extends DtoWithId> extends AbstractReadOnlyController<D> {
	
	@Autowired
	public AbstractController(ApplicationEventPublisher eventPublisher) {
		super(eventPublisher);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public D createResources(@Valid @RequestBody D dto) {
		log.debug("Received request to createResources() with params dto={}", dto);
		RestPrecondition.checkRequestElementNotNull(dto);
        RestPrecondition.checkRequestState(dto.getId() == null);
        
        return createResourcesInternal(dto);
	}
	
	protected abstract D createResourcesInternal(D dto);
	
	@PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateResources(@PathVariable Long id, @Valid @RequestBody D dto) {
		log.debug("Received request to updateResources() with params id={}, dto={}", id, dto);
		RestPrecondition.checkRequestElementNotNull(id);
		RestPrecondition.checkRequestElementNotNull(dto);
        RestPrecondition.checkRequestElementNotNull(dto.getId());
        RestPrecondition.checkRequestState(dto.getId() == id);
        //RestPrecondition.checkNotNull(getService().findOne(dto.getId()));
        
        updateResourcesInternal(dto);
	}
	
    protected abstract void updateResourcesInternal(D dto);
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteResource(@PathVariable Long id) {
		log.debug("Received request to deleteResource() with params id={}", id);
		RestPrecondition.checkRequestElementNotNull(id);
		//RestPrecondition.checkNotNull(getService().findOne(id));
        
		deleteResourceInternal(id);
	}
	
	 protected abstract void deleteResourceInternal(final long id);

}
