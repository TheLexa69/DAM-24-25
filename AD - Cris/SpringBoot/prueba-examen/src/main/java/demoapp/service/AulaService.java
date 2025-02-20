package demoapp.service;

import demoapp.model.entity.Aula;
import demoapp.model.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaService {
    @Autowired
    private AulaRepository repository;

    public Aula save(Aula aula) {
        return repository.save(aula);
    }

}