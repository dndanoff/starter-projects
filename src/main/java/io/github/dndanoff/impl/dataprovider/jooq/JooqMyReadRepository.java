package io.github.dndanoff.impl.dataprovider.jooq;

import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.dndanoff.core.business_case.dao.ReadRepository;
import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import io.github.dndanoff.core.vo.SearchInput;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class JooqMyReadRepository implements ReadRepository<Entity> {

    private final DSLContext create;

    @Autowired
    public JooqMyReadRepository(DSLContext create) {
        this.create = create;
    }

	@Override
	public ResultList<Entity> findAll(ListInput listInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Entity> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultList<Entity> search(ListInput listInput, SearchInput searchInput) {
		// TODO Auto-generated method stub
		return null;
	}

}