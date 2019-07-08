package io.github.dndanoff.core.business_case;

import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.entity.TeamMember;

public interface TeamMemberReadRepository extends ReadRepository<TeamMember>{
	public static final String DEFAULT_SORT_BY = "id";
	public static final String DEFAULT_ORDER = "ASC";
	public static final int DEFAULT_OFFSET = 0;
	public static final int DEFAULT_LIMIT = 200;
}
