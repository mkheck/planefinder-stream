package com.thehecklers.planefinderstream;

import org.springframework.data.repository.CrudRepository;

public interface PlaneRepository extends CrudRepository<Aircraft, String> {
}
