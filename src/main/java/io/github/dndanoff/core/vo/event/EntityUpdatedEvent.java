package io.github.dndanoff.core.vo.event;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

import io.github.dndanoff.core.entity.Entity;
import lombok.Data;

@Data
public class EntityUpdatedEvent<E extends Entity> implements ResolvableTypeProvider {

	private final E entity;

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(entity));
	}
}
