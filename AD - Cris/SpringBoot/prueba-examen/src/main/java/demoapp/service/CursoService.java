package demoapp.service;

import demoapp.model.entity.Curso;
import demoapp.model.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public List<Curso> listar() {
        return repository.findAll();
    }

    public Curso crear(Curso curso) {
        return repository.save(curso);
    }

    public Curso actualizar(Long id, Curso curso) {
        curso.setId(id);
        return repository.save(curso);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Curso obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Curso save(Curso curso) {
        return repository.save(curso);
    }
}