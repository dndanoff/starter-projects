package io.github.dndanoff.core.business_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.common.ListBusinessCase;
import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.TeamMember;
import io.github.dndanoff.core.vo.ListInput;

@Service
public class TeamMemberListBusinessCase extends ListBusinessCase<TeamMember>{

	@Autowired
	public TeamMemberListBusinessCase(ReadRepository<TeamMember> repo, Validator<ListInput> validator) {
		super(repo, validator);
	}

}
