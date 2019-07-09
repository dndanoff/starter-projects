package io.github.dndanoff.entry_point.web.domain;

import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.service.GenericObjectConverter;
import io.github.dndanoff.core.entity.Contact;
import io.github.dndanoff.core.entity.ContactType;

@Service
public class ContactMapper implements GenericObjectConverter<Contact, ContactDto> {
	
	public Contact dtoToEntity(ContactDto dto) {
		ContactType type = new ContactType(dto.getType().getId(), dto.getType().getName());
		type.setDescription(dto.getType().getDescription());
		type.setPriority(dto.getType().getPriority());
		Contact entity = new Contact(type, dto.getValue());
		
		return entity;
	}
	
	public ContactDto entityToDto(Contact entity) {
		ContactTypeDto type = new ContactTypeDto();
		type.setDescription(entity.getType().getDescription());
		type.setPriority(entity.getType().getPriority());
		type.setId(entity.getType().getId());
		type.setName(entity.getType().getName());
		
		return new ContactDto(type, entity.getValue());
	}
}
