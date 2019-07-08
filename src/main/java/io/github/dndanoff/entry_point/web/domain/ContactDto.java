package io.github.dndanoff.entry_point.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import io.github.dndanoff.entry_point.web.dto.DtoView;
import io.github.dndanoff.entry_point.web.dto.DtoWithId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "name", "description", "value"})
public final class ContactDto implements DtoWithId{
	@JsonView(DtoView.Aggregated.class)
	private Integer id;
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private String name;
	@JsonView(DtoView.Aggregated.class)
	private String description;
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private String value;
	@JsonView(DtoView.Aggregated.class)
	private Integer priority;
}
