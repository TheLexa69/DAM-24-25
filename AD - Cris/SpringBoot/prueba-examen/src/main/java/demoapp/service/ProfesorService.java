package demoapp.service;

import demoapp.model.entity.Profesor;
import demoapp.model.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository repository;

    public List<Profesor> listar() {
        return repository.findAll();
    }

    public Profesor save(Profesor profesor) {
        return repository.save(profesor);
    }

    public Profesor actualizar(Long id, Profesor profesor) {
        profesor.setId(id);
        return repository.save(profesor);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Profesor obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}