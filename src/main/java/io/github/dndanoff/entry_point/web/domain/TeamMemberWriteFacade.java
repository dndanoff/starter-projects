package io.github.dndanoff.entry_point.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.TeamMemberCreateBusinessCase;
import io.github.dndanoff.core.business_case.TeamMemberDeleteBusinessCase;
import io.github.dndanoff.core.business_case.TeamMemberListBusinessCase;
import io.github.dndanoff.core.business_case.TeamMemberSearchBusinessCase;
import io.github.dndanoff.core.business_case.TeamMemberUpdateBusinessCase;
import io.github.dndanoff.entry_point.web.facade.Facade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamMemberWriteFacade implements Facade<TeamMemberDto>{
	
	private final TeamMemberUpdateBusinessCase updateBc;
	private final TeamMemberCreateBusinessCase createBc;
	private final TeamMemberDeleteBusinessCase deleteBc;
	private final TeamMemberMapper mapper;
	
	@Autowired
	public TeamMemberWriteFacade(TeamMemberCreateBusinessCase createBc, TeamMemberUpdateBusinessCase updateBc, TeamMemberDeleteBusinessCase deleteBc, TeamMemberMapper mapper) {
		this.createBc = createBc;
		this.updateBc = updateBc;
		this.deleteBc = deleteBc;
		this.mapper = mapper;
	}

	protected TeamMemberDto create(TeamMemberDto dto) {
		return mapper.entityToDto(createBc.create(mapper.dtoToEntity(dto)));
	}

	protected void update(TeamMemberDto dto) {
		updateBc.update(mapper.dtoToEntity(dto));
	}

	protected void delete(long id) {
		deleteBc.delete((int) id);
	}
}
