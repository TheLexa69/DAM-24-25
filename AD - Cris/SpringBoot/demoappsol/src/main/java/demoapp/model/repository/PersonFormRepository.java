package demoapp.model.repository;

import demoapp.model.entity.PersonForm;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonFormRepository extends CrudRepository<PersonForm, Long> {
    Optional<PersonForm> findByName(String name);
}