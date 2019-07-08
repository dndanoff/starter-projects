package io.github.dndanoff.entry_point.web.domain;

import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.service.GenericObjectConverter;
import io.github.dndanoff.core.entity.Technology;

@Service
public class TechnologyMapper implements GenericObjectConverter<Technology, TechnologyDto> {
	
	public Technology dtoToEntity(TechnologyDto dto) {
		Technology entity = new Technology(dto.getId(), dto.getName());
		entity.setDescription(dto.getDescription());
		
		return entity;
	}
	
	public TechnologyDto entityToDto(Technology entity) {
		return new TechnologyDto(entity.getId(), entity.getName(), entity.getDescription());
	}
}
