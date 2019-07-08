package io.github.dndanoff.core.business_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.common.SearchBusinessCase;
import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.TeamMember;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.SearchInput;

@Service
public class TeamMemberSearchBusinessCase extends SearchBusinessCase<TeamMember>{

	@Autowired
	public TeamMemberSearchBusinessCase(ReadRepository<TeamMember> repo, Validator<SearchInput> searchValidator, Validator<ListInput> validator) {
		super(repo, searchValidator, validator);
	}

}
