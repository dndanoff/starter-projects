package io.github.dndanoff.core.business_case;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.service.Validator;
import io.github.dndanoff.core.entity.TeamMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamMemberValidator implements Validator<TeamMember>{
	
	@Override
	public boolean isModelValid(TeamMember entity) {
		if(StringUtils.isBlank(entity.getFirstName())) {
			return false;
		}
		
		if(StringUtils.isBlank(entity.getLastName())) {
			return false;
		}
		
		if(entity.getHireDate() == null) {
			return false;
		}
		
		return true;
	}

}
