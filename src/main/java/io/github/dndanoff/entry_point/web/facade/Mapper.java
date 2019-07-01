package io.github.dndanoff.entry_point.web.facade;

import io.github.dndanoff.core.Model;
import io.github.dndanoff.core.business_case.service.GenericObjectConverter;
import io.github.dndanoff.entry_point.web.dto.Dto;

public interface Mapper <E extends Model, D extends Dto> extends GenericObjectConverter<E,D>{

}
