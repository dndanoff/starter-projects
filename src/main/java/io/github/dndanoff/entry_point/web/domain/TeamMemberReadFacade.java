package io.github.dndanoff.entry_point.web.domain;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dndanoff.core.business_case.TeamMemberListBusinessCase;
import io.github.dndanoff.core.business_case.TeamMemberSearchBusinessCase;
import io.github.dndanoff.core.entity.TeamMember;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import io.github.dndanoff.core.vo.SearchInput;
import io.github.dndanoff.entry_point.web.dto.ListInputDto;
import io.github.dndanoff.entry_point.web.dto.MetadataDto;
import io.github.dndanoff.entry_point.web.dto.ResultListDto;
import io.github.dndanoff.entry_point.web.dto.SearchInputDto;
import io.github.dndanoff.entry_point.web.facade.Facade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamMemberReadFacade implements Facade<TeamMemberDto>{
	
	private final TeamMemberListBusinessCase listBc;
	private final TeamMemberSearchBusinessCase searchBc;
	private final TeamMemberMapper mapper;
	
	@Autowired
	public TeamMemberReadFacade(TeamMemberListBusinessCase listBc, TeamMemberSearchBusinessCase searchBc, TeamMemberMapper mapper) {
		this.listBc = listBc;
		this.searchBc = searchBc;
		this.mapper = mapper;
	}
	
    public ResultListDto<TeamMemberDto> getAll(ListInputDto listInput) {
    	ResultList<TeamMember> members = listBc.getAll(convertToEntity(listInput));
    	return convertToDto(members);
    }

    public TeamMemberDto getById(Integer id) {
    	Optional<TeamMember> memberOpt = listBc.getById(id);
    	if(!memberOpt.isPresent()) {
    		return null;
    	}
    	return mapper.entityToDto(memberOpt.get());
    }
    
    public ResultListDto<TeamMemberDto> search(SearchInputDto searchInput, ListInputDto listInput) {
    	ResultList<TeamMember> members = searchBc.search(convertToEntity(searchInput), convertToEntity(listInput));
    	return convertToDto(members);
    }
    
    private ListInput convertToEntity(ListInputDto listInput) {
    	return ListInput.builder().limit(listInput.getLimit()).offset(listInput.getOffset()).order(listInput.getOrder()).sortBy(listInput.getSortBy()).build();
    }
    
    private SearchInput convertToEntity(SearchInputDto searchInput) {
    	return SearchInput.builder().q(searchInput.getQ()).build();
    }
    
    private ResultListDto<TeamMemberDto> convertToDto(ResultList<TeamMember> members) {
    	ResultListDto<TeamMemberDto> membersDto = new ResultListDto<>();
    	membersDto.setDtos(members.getEntities().stream().map(m -> mapper.entityToDto(m)).collect(Collectors.toList()));
    	MetadataDto metaDto = new MetadataDto();
    	metaDto.setLimit(members.getMetadata().getLimit());
    	metaDto.setOffset(members.getMetadata().getOffset());
    	metaDto.setOrder(members.getMetadata().getOrder());
    	metaDto.setSortBy(members.getMetadata().getSortBy());
    	metaDto.setTotal(members.getMetadata().getTotal());
    	membersDto.setMetadata(metaDto);
    	
    	return membersDto;
    }
}
