package io.github.dndanoff.core.business_case.service;

import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.event.EntityUpdatedEvent;

public interface EntityUpdatedEventHandler <E extends Entity>{
	public void handle(EntityUpdatedEvent<E> event);
}
