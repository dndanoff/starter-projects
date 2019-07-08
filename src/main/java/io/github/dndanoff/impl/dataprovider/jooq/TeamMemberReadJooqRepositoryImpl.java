package io.github.dndanoff.impl.dataprovider.jooq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Select;
import org.jooq.SelectConditionStep;
import org.jooq.SelectLimitStep;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.core.business_case.TeamMemberReadRepository;
import io.github.dndanoff.core.entity.Contact;
import io.github.dndanoff.core.entity.TeamMember;
import io.github.dndanoff.core.entity.Technology;
import io.github.dndanoff.core.entity.Title;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.Metadata;
import io.github.dndanoff.core.vo.ResultList;
import io.github.dndanoff.core.vo.SearchInput;
import io.github.dndanoff.db.Tables;
import io.github.dndanoff.db.tables.records.ContactTypeRecord;
import io.github.dndanoff.db.tables.records.MemberRecord;
import io.github.dndanoff.db.tables.records.TechnologyRecord;
import io.github.dndanoff.db.tables.records.TitleRecord;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class TeamMemberReadJooqRepositoryImpl implements TeamMemberReadRepository {

    private final DSLContext create;

    @Autowired
    public TeamMemberReadJooqRepositoryImpl(DSLContext create) {
        this.create = create;
    }

	@Override
	public ResultList<TeamMember> findAll(ListInput listInput) {
		log.debug("Calling findAll with params: listInput={}",listInput);
		
		Select<Record> sql = create
        		.select(Stream.of(Tables.MEMBER.fields(),
	        						Tables.TITLE.fields())
	        				.flatMap(f->Arrays.stream(f))
	        				.collect(Collectors.toList()))
                .from(Tables.MEMBER)
                .leftOuterJoin(Tables.TITLE).on(Tables.MEMBER.TITLE_ID.eq(Tables.TITLE.ID));
                
        sql = addSortClause(sql, listInput.getSortBy(), listInput.getOrder());
        sql = addOffsetClause(sql, listInput.getOffset(), listInput.getLimit());
        
        List<TeamMember> members = getEntityFromSQL(sql);
        Integer total = create
                .selectCount()
                .from(Tables.MEMBER)
                .fetchOne(0, Integer.class);
        Metadata metadata = new Metadata(listInput.getSortBy(), listInput.getOrder(), listInput.getOffset(), listInput.getLimit(), total);
		
		log.debug("findAll fetched result={}", members);
		return new ResultList<>(members, metadata);
	}

	@Override
	public Optional<TeamMember> findById(Integer id) {
		log.debug("Calling findById with params: id={}",id);
		
		Select<Record> sql = create
        		.select(Stream.of(Tables.MEMBER.fields(),
	        						Tables.TITLE.fields())
	        				.flatMap(f->Arrays.stream(f))
	        				.collect(Collectors.toList()))
                .from(Tables.MEMBER)
                .leftOuterJoin(Tables.TITLE).on(Tables.MEMBER.TITLE_ID.eq(Tables.TITLE.ID))
                .where(Tables.MEMBER.ID.eq(id.longValue()));
        
		List<TeamMember> entities = getEntityFromSQL(sql);
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        log.debug("findById fetched result={}", entities.get(0));
        
        return Optional.ofNullable(entities.get(0));
	}
	
	@Override
	public ResultList<TeamMember> search(ListInput listInput, SearchInput searchInput) {
		log.debug("Calling search with params: listInput={}, searchInput={}", listInput, searchInput);
		if (StringUtils.isEmpty(searchInput.getQ())) {
            log.debug("We will call findAll, because free text is null or empty.");
            return findAll(listInput);
        }

        List<Condition> conditionsAnd = new ArrayList<>();
        if (StringUtils.isNotEmpty(searchInput.getQ())) {
            List<Condition> conditionsOR = new ArrayList<>();
            Arrays.stream(Tables.MEMBER.fields()).forEach(f->conditionsOR.add(f.likeIgnoreCase("%" + searchInput.getQ() + "%")));
            Condition conditionForSearchOR = DSL.or(conditionsOR);
            conditionsAnd.add(conditionForSearchOR);
        }
        Condition conditionForSearch = DSL.and(conditionsAnd);
        
        Select<Record> sql = create
        		.select(Stream.of(Tables.MEMBER.fields(),
	        						Tables.TITLE.fields())
	        				.flatMap(f->Arrays.stream(f))
	        				.collect(Collectors.toList()))
                .from(Tables.MEMBER)
                .leftOuterJoin(Tables.TITLE).on(Tables.MEMBER.TITLE_ID.eq(Tables.TITLE.ID))
                .where(conditionForSearch);
                
        sql = addSortClause(sql, listInput.getSortBy(), listInput.getOrder());
        sql = addOffsetClause(sql, listInput.getOffset(), listInput.getLimit());
        
        List<TeamMember> members = getEntityFromSQL(sql);
        Integer total = create
                .selectCount()
                .from(Tables.MEMBER)
                .fetchOne(0, Integer.class);
        Metadata metadata = new Metadata(listInput.getSortBy(), listInput.getOrder(), listInput.getOffset(), listInput.getLimit(), total);
		
        
        log.debug("search fetched result={}", members);
		return new ResultList<>(members, metadata);
	}
	
	private Select<Record> addSortClause(Select<Record> sql, String sortBy, String order) {
    	Field<?> sortField = null;
	
    	if (StringUtils.isNotEmpty(sortBy)) {
			for(Field<?> field : Tables.MEMBER.fields()) {
        		if (field.getName().replaceAll("_", "").toUpperCase().equals(sortBy.toUpperCase())) {
        			sortField = field;
                    break;
                }
        	}
        }
    	
    	if(sortField == null) {
    		sortField = Tables.MEMBER.ID;
    	}
    	
    	if (StringUtils.isEmpty(order)) {
    		order = DEFAULT_ORDER;
    	}
    	
    	return ((SelectConditionStep<Record>) sql).orderBy(sortField.sort(SortOrder.valueOf(order)));
    }
    
    private Select<Record> addOffsetClause(Select<Record> sql, Integer offset, Integer limit) {
    	if (offset == null) {
    		offset = DEFAULT_OFFSET;
    	}
    	
    	if (limit == null) {
    		limit = DEFAULT_LIMIT;
    	}
    	
    	return ((SelectLimitStep<Record>) sql).offset(offset).limit(limit);
    }
    
    private List<TeamMember> getEntityFromSQL(Select<Record> masterSql) {
    	List<TeamMember> members = masterSql.fetch(r -> {
    		MemberRecord memberRecord = r.into(Tables.MEMBER).into(MemberRecord.class);
    		TeamMember member = new TeamMember(memberRecord.getFirstName(), memberRecord.getLastName(), memberRecord.getHireDate());
    		member.setId(memberRecord.getId().intValue());
    		member.setActive(memberRecord.getActive());
    		member.setPhotoUrl(memberRecord.getPhotoUrl());
    		
            if (member.getId() != null) {
            	TitleRecord titleRecord = r.into(Tables.TITLE).into(TitleRecord.class);
                Title title = new Title(titleRecord.getId().intValue(), titleRecord.getName());
            	title.setDescription(titleRecord.getDescription());
            	member.setTitle(title);
            }
            return member;
        });
    	
    	Map<Integer, List<Contact>> contacts = create.select(Stream.of(Tables.MEMBER_CONTACT.fields(),
															Tables.CONTACT_TYPE.fields())
													.flatMap(f->Arrays.stream(f))
													.collect(Collectors.toList()))        
									.from(Tables.MEMBER_CONTACT)
					                .leftOuterJoin(Tables.CONTACT_TYPE).on(Tables.MEMBER_CONTACT.CONTACT_TYPE_ID.eq(Tables.CONTACT_TYPE.ID))
					                .where(Tables.MEMBER_CONTACT.MEMBER_ID.in(members.stream().map(a->a.getId()).collect(Collectors.toList())))
					                .fetchGroups(r -> {
							            Integer id = r.into(Tables.MEMBER_CONTACT.MEMBER_ID).into(Integer.class);
							            return id;
							        }, r -> {
							            ContactTypeRecord contactRecord = r.into(Tables.CONTACT_TYPE).into(ContactTypeRecord.class);
							            String contactValue = r.into(Tables.MEMBER_CONTACT.VALUE).into(String.class);
							            Contact contact = new Contact(contactRecord.getId().intValue(), contactRecord.getName(), contactValue);
							            contact.setPriority(contactRecord.getPriority());
							            contact.setDescription("");
							            if (contact.getId() == null) {
							                return null;
							            }
							            
							            return contact;
							        });
    	
    	Map<Integer, List<Technology>> technologies = create.select(Stream.of(Tables.MEMBER_TECHNOLOGY.fields(),
																	Tables.TECHNOLOGY.fields())
															.flatMap(f->Arrays.stream(f))
															.collect(Collectors.toList()))        
								.from(Tables.MEMBER_TECHNOLOGY)
								.leftOuterJoin(Tables.TECHNOLOGY).on(Tables.MEMBER_TECHNOLOGY.TECHNOLOGY_ID.eq(Tables.TECHNOLOGY.ID))
								.where(Tables.MEMBER_TECHNOLOGY.MEMBER_ID.in(members.stream().map(a->a.getId()).collect(Collectors.toList())))
								.fetchGroups(r -> {
										Integer id = r.into(Tables.MEMBER_TECHNOLOGY.MEMBER_ID).into(Integer.class);
										return id;
									}, r -> {
										TechnologyRecord technologyRecord = r.into(Tables.TECHNOLOGY).into(TechnologyRecord.class);
										Technology technology = new Technology(technologyRecord.getId().intValue(), technologyRecord.getName());
										technology.setDescription(technologyRecord.getDescription());
										if (technology.getId() == null) {
											return null;
										}
									
									return technology;
								});
    	
    	members.stream().forEach(a->a.setContacts(contacts.getOrDefault(a.getId(), new ArrayList<>())));
    	members.stream().forEach(a->a.setKnownTechnologies(technologies.getOrDefault(a.getId(), new ArrayList<>())));
        
        return members;
    }

}