package io.github.dndanoff.entry_point.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dndanoff.entry_point.web.controller.AbstractReadController;
import io.github.dndanoff.entry_point.web.dto.ListInputDto;
import io.github.dndanoff.entry_point.web.dto.ResultListDto;
import io.github.dndanoff.entry_point.web.dto.SearchInputDto;

@RestController
@RequestMapping("/v1/team-members")
public class TeamMemberReadController extends AbstractReadController<TeamMemberDto>{

	private final TeamMemberReadFacade facade;
	
	@Autowired
	public TeamMemberReadController(TeamMemberReadFacade facade) {
		super();
		this.facade = facade;
	}

	@Override
	protected ResultListDto<TeamMemberDto> findResourcesPaginatedInternal(ListInputDto listInput) {
		return facade.getAll(listInput);
	}

	@Override
	protected ResultListDto<TeamMemberDto> searchResourcesPaginatedInternal(SearchInputDto searchInput,
			ListInputDto listInput) {
		return facade.search(searchInput, listInput);
	}

	@Override
	protected TeamMemberDto findOneResourceInternal(Long id) {
		return facade.getById(id.intValue());
	}

	@Override
	protected TeamMemberReadFacade getServiceFacade() {
		return this.facade;
	}

}
