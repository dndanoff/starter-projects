package io.github.dndanoff.core.business_case.service;

import io.github.dndanoff.core.entity.Entity;

public interface StateProducer <E extends Entity> {

	public void produceState(E entity);

}