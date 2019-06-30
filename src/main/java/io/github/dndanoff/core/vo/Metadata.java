package io.github.dndanoff.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata implements ValueObject {
	String sortBy;
	String order;
	Integer limit;
	Integer offset;
	Integer total;
}
