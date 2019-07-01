package io.github.dndanoff.core.vo;

import java.util.List;

import io.github.dndanoff.core.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultList <E extends Model> implements ValueObject {
	
	List<E> entities;
	Metadata metadata;

}
