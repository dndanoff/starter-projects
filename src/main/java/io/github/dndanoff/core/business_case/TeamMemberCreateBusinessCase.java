package io.github.dndanoff.core.business_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.common.CreateBusinessCase;
import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.business_case.dao.WriteRepository;
import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.TeamMember;

@Service
public class TeamMemberCreateBusinessCase extends CreateBusinessCase<TeamMember>{

	protected final ApplicationEventPublisher eventPublisher;
	
	@Autowired
	public TeamMemberCreateBusinessCase(ReadRepository<TeamMember> readRepo, WriteRepository<TeamMember> writeRepo, Validator<TeamMember> validator, ApplicationEventPublisher eventPublisher) {
		super(readRepo, writeRepo,validator);
		this.eventPublisher = eventPublisher;
	}

}
