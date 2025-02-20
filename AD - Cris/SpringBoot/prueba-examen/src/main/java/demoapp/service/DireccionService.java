package demoapp.service;

import demoapp.model.entity.Direccion;
import demoapp.model.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
    @Autowired
    private DireccionRepository repository;

    public Direccion save(Direccion direccion) {
        return repository.save(direccion);
    }
}
