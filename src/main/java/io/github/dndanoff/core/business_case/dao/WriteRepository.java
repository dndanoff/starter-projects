package io.github.dndanoff.core.business_case.dao;

import io.github.dndanoff.core.entity.Entity;

public interface WriteRepository <E extends Entity>{
	public E create(E entity);
	public int update(E entity);
	public int delete(E entity);
}
