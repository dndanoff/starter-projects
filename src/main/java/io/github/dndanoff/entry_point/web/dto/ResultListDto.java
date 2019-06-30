package io.github.dndanoff.entry_point.web.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "entities", "metadata"})
public class ResultListDto <D extends Dto> implements Dto {
	
	List<D> dtos;
	MetadataDto metadata;

}
