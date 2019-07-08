package io.github.dndanoff.entry_point.web.domain;

import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.service.GenericObjectConverter;
import io.github.dndanoff.core.entity.Contact;

@Service
public class ContactMapper implements GenericObjectConverter<Contact, ContactDto> {
	
	public Contact dtoToEntity(ContactDto dto) {
		Contact entity = new Contact(dto.getId(), dto.getName(), dto.getValue());
		entity.setDescription(dto.getDescription());
		entity.setPriority(dto.getPriority());
		
		return entity;
	}
	
	public ContactDto entityToDto(Contact entity) {
		return new ContactDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getValue(), entity.getPriority());
	}
}
