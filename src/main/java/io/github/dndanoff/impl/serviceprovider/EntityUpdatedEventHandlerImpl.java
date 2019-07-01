package io.github.dndanoff.impl.serviceprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.service.EntityUpdatedEventHandler;
import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.event.EntityUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EntityUpdatedEventHandlerImpl implements EntityUpdatedEventHandler<Entity> {
	
	
	@Autowired
	public EntityUpdatedEventHandlerImpl() {
		
	}

	//@Async
	@Override
	@EventListener
	public void handle(EntityUpdatedEvent<Entity> event) {
		// TODO Auto-generated method stub
		
	}

}
