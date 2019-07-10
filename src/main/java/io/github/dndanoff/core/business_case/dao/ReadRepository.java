package io.github.dndanoff.core.business_case.dao;

import java.util.Optional;

import io.github.dndanoff.core.entity.Entity;
import io.github.dndanoff.core.vo.ListInput;
import io.github.dndanoff.core.vo.ResultList;
import io.github.dndanoff.core.vo.SearchInput;

public interface ReadRepository <E extends Entity>{
	
	public boolean checkIfEntityExists(E entity);
	public ResultList<E> findAll(ListInput listInput);
	public Optional<E> findById(Integer id);
	public ResultList<E> search(ListInput listInput, SearchInput searchInput);
}
