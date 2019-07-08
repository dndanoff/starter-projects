package io.github.dndanoff.entry_point.web.domain;

import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.service.GenericObjectConverter;
import io.github.dndanoff.core.entity.Title;

@Service
public class TitleMapper implements GenericObjectConverter<Title, TitleDto> {
	
	public Title dtoToEntity(TitleDto dto) {
		Title entity = new Title(dto.getId(), dto.getName());
		entity.setDescription(dto.getDescription());
		
		return entity;
	}
	
	public TitleDto entityToDto(Title entity) {
		return new TitleDto(entity.getId(), entity.getName(), entity.getDescription());
	}
}
