package io.github.dndanoff.entry_point.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.service.GenericObjectConverter;
import io.github.dndanoff.core.entity.TeamMember;

@Service
public class TeamMemberMapper implements GenericObjectConverter<TeamMember, TeamMemberDto> {
	
	private final ContactMapper contactMapper;
	private final TechnologyMapper technologyMapper;
	private final TitleMapper titleMapper;
	
	@Autowired
	public TeamMemberMapper(ContactMapper contactMapper, TechnologyMapper technologyMapper, TitleMapper titleMapper) {
		this.contactMapper = contactMapper;
		this.technologyMapper = technologyMapper;
		this.titleMapper = titleMapper;
	}
	
	public TeamMember dtoToEntity(TeamMemberDto dto) {
		TeamMember entity = new TeamMember(dto.getFirstName(), dto.getLastName(), dto.getHireDate());
		entity.setId(dto.getId());
		entity.setPhotoUrl(dto.getPhotoUrl());
		entity.setActive(dto.getActive());
		entity.setTitle(titleMapper.dtoToEntity(dto.getTitle()));;
		entity.setContacts(contactMapper.dtoToEntity(dto.getContacts()));
		entity.setKnownTechnologies(technologyMapper.dtoToEntity(dto.getKnownTechnologies()));
		
		return entity;
	}
	
	public TeamMemberDto entityToDto(TeamMember entity) {
		TeamMemberDto dto = new TeamMemberDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setPhotoUrl(entity.getPhotoUrl());
		dto.setTitle(titleMapper.entityToDto(entity.getTitle()));
		dto.setContacts(contactMapper.entityToDto(entity.getContacts()));
		dto.setKnownTechnologies(technologyMapper.entityToDto(entity.getKnownTechnologies()));
		
		return dto;
	}
}
