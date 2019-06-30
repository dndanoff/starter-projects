package io.github.dndanoff.core.business_case.service;

import io.github.dndanoff.core.Model;

public interface Validator <E extends Model> {
    public boolean isModelValid(E entity);
}
