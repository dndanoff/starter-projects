package io.github.dndanoff.core.entity;

import lombok.Data;

@Data
public final class Title implements Entity{
	private final Integer id;
	private final String name;
	private String description;
}
