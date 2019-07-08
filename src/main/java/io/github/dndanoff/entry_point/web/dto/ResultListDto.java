package io.github.dndanoff.entry_point.web.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(DtoView.Aggregated.class)
@JsonPropertyOrder(value = { "entities", "metadata"})
public class ResultListDto <D extends Dto> implements Dto {
	private List<D> dtos;
	private MetadataDto metadata;

}
