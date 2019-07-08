package io.github.dndanoff.core.business_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.common.DeleteBusinessCase;
import io.github.dndanoff.core.business_case.dao.WriteRepository;
import io.github.dndanoff.core.entity.TeamMember;

@Service
public class TeamMemberDeleteBusinessCase extends DeleteBusinessCase<TeamMember>{

	protected final ApplicationEventPublisher eventPublisher;
	
	@Autowired
	public TeamMemberDeleteBusinessCase(WriteRepository<TeamMember> repo, ApplicationEventPublisher eventPublisher) {
		super(repo);
		this.eventPublisher = eventPublisher;
	}

}
