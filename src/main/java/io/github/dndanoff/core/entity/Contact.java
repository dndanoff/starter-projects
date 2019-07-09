package io.github.dndanoff.core.entity;

import lombok.Data;

@Data
public final class Contact implements Entity{
	private final ContactType type;
	private final String value;
	
	@Override
	public Integer getId() {
		return type.getId();
	}
}
