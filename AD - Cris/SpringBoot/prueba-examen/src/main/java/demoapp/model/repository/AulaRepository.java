package demoapp.model.repository;

import demoapp.model.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    Optional<Aula> findByNombre(String nombre);

}
