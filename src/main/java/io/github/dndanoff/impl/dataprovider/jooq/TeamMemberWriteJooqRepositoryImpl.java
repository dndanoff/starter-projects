package io.github.dndanoff.impl.dataprovider.jooq;

import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.dndanoff.core.business_case.TeamMemberWriteRepository;
import io.github.dndanoff.core.entity.TeamMember;
import io.github.dndanoff.db.Tables;
import io.github.dndanoff.db.tables.records.MemberContactRecord;
import io.github.dndanoff.db.tables.records.MemberRecord;
import io.github.dndanoff.db.tables.records.MemberTechnologyRecord;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class TeamMemberWriteJooqRepositoryImpl implements TeamMemberWriteRepository {

    private final DSLContext create;

    @Autowired
    public TeamMemberWriteJooqRepositoryImpl(DSLContext create) {
        this.create = create;
    }

   
	@Override
	@Transactional
	public TeamMember create(TeamMember entity) {
		log.debug("Calling create with arguments entity={}", entity);
		if(entity == null) {
			log.debug("Skipping create the provided param is null");
			throw new IllegalArgumentException("entity is null");
		}
        MemberRecord record = create.newRecord(Tables.MEMBER, entity);
        if(entity.getTitle() != null) {
        	record.setTitleId(entity.getTitle().getId().longValue());
        }
        
        record.store();
        entity.setId(record.into(MemberRecord.class).getId().intValue());
        
        if(!entity.getKnownTechnologies().isEmpty()) {
        	create.batchInsert(entity.getKnownTechnologies().stream()
        			.map(t-> { 
        				MemberTechnologyRecord techRecord = create.newRecord(Tables.MEMBER_TECHNOLOGY);
        				techRecord.setTechnologyId(t.getId().longValue());
        				techRecord.setMemberId(entity.getId().longValue());
						return techRecord;})
        			.collect(Collectors.toList()));
        }
        
        if(!entity.getContacts().isEmpty()) {
        	create.batchInsert(entity.getContacts().stream()
        			.map(c-> { 
        				MemberContactRecord contactRecord = create.newRecord(Tables.MEMBER_CONTACT);
        				contactRecord.setContactTypeId(c.getId().longValue());
        				contactRecord.setValue(c.getValue());
        				contactRecord.setMemberId(entity.getId().longValue());
						return contactRecord;})
        			.collect(Collectors.toList()));
        }
        
        return entity;
	}

	@Override
	@Transactional
	public int update(TeamMember entity) {
		log.debug("Calling update with arguments entity={}", entity);
		if (entity == null) {
			log.debug("Skipping update entity is null.");
		    throw new IllegalArgumentException("entity is null");
		}
		
		MemberRecord record = create.newRecord(Tables.MEMBER, entity);
		if(entity.getTitle() != null) {
			record.setTitleId(entity.getTitle().getId().longValue());
		}
		
		create.deleteFrom(Tables.MEMBER_TECHNOLOGY).where(Tables.MEMBER_TECHNOLOGY.MEMBER_ID.eq(entity.getId().longValue())).execute();
		if(!entity.getKnownTechnologies().isEmpty()) {
        	create.batchInsert(entity.getKnownTechnologies().stream()
        			.map(t-> { 
        				MemberTechnologyRecord techRecord = create.newRecord(Tables.MEMBER_TECHNOLOGY);
        				techRecord.setTechnologyId(t.getId().longValue());
        				techRecord.setMemberId(entity.getId().longValue());
						return techRecord;})
        			.collect(Collectors.toList()));
        }
        
		create.deleteFrom(Tables.MEMBER_CONTACT).where(Tables.MEMBER_CONTACT.MEMBER_ID.eq(entity.getId().longValue())).execute();
        if(!entity.getContacts().isEmpty()) {
        	create.batchInsert(entity.getContacts().stream()
        			.map(c-> { 
        				MemberContactRecord contactRecord = create.newRecord(Tables.MEMBER_CONTACT);
        				contactRecord.setContactTypeId(c.getId().longValue());
        				contactRecord.setValue(c.getValue());
        				contactRecord.setMemberId(entity.getId().longValue());
						return contactRecord;})
        			.collect(Collectors.toList()));
        }
		
		return record.update();
	}

	@Override
	public int delete(Integer id) {
		log.debug("Calling delete with arguments id={}", id);
        if (id == null) {
            log.debug("Skipping delete. Reason: id is null.");
            throw new IllegalArgumentException("id is null");
        }

        return create.update(Tables.MEMBER)
                .set(Tables.MEMBER.ACTIVE, Boolean.FALSE)
                .where(Tables.MEMBER.ID.eq(id.longValue()))
                .execute();
	}

	

}