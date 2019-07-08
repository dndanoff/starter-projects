package io.github.dndanoff.impl.dataprovider.jooq;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.core.business_case.TeamMemberWriteRepository;
import io.github.dndanoff.core.entity.TeamMember;
import io.github.dndanoff.db.Tables;
import io.github.dndanoff.db.tables.records.MemberRecord;
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
        return entity;
	}

	@Override
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