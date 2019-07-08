package io.github.dndanoff.entry_point.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.TeamMemberListBusinessCase;
import io.github.dndanoff.core.business_case.TeamMemberSearchBusinessCase;
import io.github.dndanoff.entry_point.web.facade.Facade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamMemberWriteFacade implements Facade<TeamMemberDto>{
	
	private final TeamMemberListBusinessCase listBc;
	private final TeamMemberSearchBusinessCase searchBc;
	private final TeamMemberMapper mapper;
	
	@Autowired
	public TeamMemberWriteFacade(TeamMemberListBusinessCase listBc, TeamMemberSearchBusinessCase searchBc, TeamMemberMapper mapper) {
		this.listBc = listBc;
		this.searchBc = searchBc;
		this.mapper = mapper;
	}

	protected TeamMemberDto create(TeamMemberDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void update(TeamMemberDto dto) {
		// TODO Auto-generated method stub
		
	}

	protected void delete(long id) {
		// TODO Auto-generated method stub
		
	}
}
