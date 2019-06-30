package io.github.dndanoff.core.business_case.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface GenericObjectConverter<E, D> {

	E dtoToEntity(D dto);
	
	D entityToDto(E entity);

	default List<E> dtoToEntity(Collection<D> dtos) {
		return Optional.ofNullable(dtos).map(Collection::stream).orElseGet(Stream::empty)
				.map(this::dtoToEntity).collect(Collectors.toList());
	}

	default List<D> entityToDto(Collection<E> entities) {
		return Optional.ofNullable(entities).map(Collection::stream).orElseGet(Stream::empty)
				.map(this::entityToDto).collect(Collectors.toList());
	}
}
