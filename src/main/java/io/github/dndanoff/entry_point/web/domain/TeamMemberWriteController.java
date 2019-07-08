package io.github.dndanoff.entry_point.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dndanoff.entry_point.web.controller.AbstractWriteController;

@RestController
@RequestMapping("/v1/team-members")
public class TeamMemberWriteController extends AbstractWriteController<TeamMemberDto>{

	private final TeamMemberWriteFacade facade;
	
	@Autowired
	public TeamMemberWriteController(ApplicationEventPublisher eventPublisher, TeamMemberWriteFacade facade) {
		super(eventPublisher);
		this.facade = facade;
	}

	@Override
	protected TeamMemberDto createResourcesInternal(TeamMemberDto dto) {
		return facade.create(dto);
	}

	@Override
	protected void updateResourcesInternal(TeamMemberDto dto) {
		facade.update(dto);
	}

	@Override
	protected void deleteResourceInternal(long id) {
		facade.delete(id);
	}



}
