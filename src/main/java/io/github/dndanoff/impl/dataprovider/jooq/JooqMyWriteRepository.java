package io.github.dndanoff.impl.dataprovider.jooq;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.core.business_case.dao.WriteRepository;
import io.github.dndanoff.core.entity.Entity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JooqMyWriteRepository implements WriteRepository<Entity> {

    private final DSLContext create;

    @Autowired
    public JooqMyWriteRepository(DSLContext create) {
        this.create = create;
    }

	@Override
	public Entity create(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Entity entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Entity entity) {
		// TODO Auto-generated method stub
		return 0;
	}
}
