package io.github.dndanoff.entry_point.web.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(DtoView.Aggregated.class)
@JsonPropertyOrder(value = { "sortBy", "order", "limit", "offset", "total" })
public class MetadataDto implements Dto {
	private String sortBy;
	private String order;
	private Integer limit;
	private Integer offset;
	private Integer total;
}
