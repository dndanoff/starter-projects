package io.github.dndanoff.entry_point.web.domain;

import java.time.LocalDate;
import java.util.List;

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
@JsonPropertyOrder({"id", "firstName", "lastName", "photoUrl", "hireDate", "title", "contacts", "knownTechnologies"})
public final class TeamMemberDto implements DtoWithId{
	@JsonView(DtoView.Aggregated.class)
	private Integer id;
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private String firstName;
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private String lastName;
	@JsonView(DtoView.Aggregated.class)
	private String photoUrl;
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private LocalDate hireDate;
	@JsonProperty(required=true)
	@JsonView(DtoView.Aggregated.class)
	private Boolean active;
	@JsonView(DtoView.Aggregated.class)
	private TitleDto title;
	@JsonView(DtoView.Detailed.class)
	private List<ContactDto> contacts;
	@JsonView(DtoView.Detailed.class)
	private List<TechnologyDto> knownTechnologies;
}
