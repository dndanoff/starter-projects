package io.github.dndanoff.entry_point.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import io.github.dndanoff.entry_point.web.dto.Dto;
import io.github.dndanoff.entry_point.web.dto.DtoView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"type", "value"})
public final class ContactDto implements Dto{
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private ContactTypeDto type;
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private String value;
}
