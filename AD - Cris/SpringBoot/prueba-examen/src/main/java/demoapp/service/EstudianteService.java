package demoapp.service;

import demoapp.model.entity.Estudiante;
import demoapp.model.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository repository;

    public List<Estudiante> listar() {
        return repository.findAll();
    }

    public Estudiante crear(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public Estudiante actualizar(Long id, Estudiante estudiante) {
        estudiante.setId(id);
        return repository.save(estudiante);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Estudiante save(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public Estudiante obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}