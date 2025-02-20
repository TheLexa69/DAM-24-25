package demoapp.config;

import demoapp.model.entity.*;
import demoapp.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private AulaService aulaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private ProfesorService profesorService;

    @PostConstruct
    public void initializeData() {
        try {
            // Crear profesores
            Profesor profesor1 = new Profesor();
            profesor1.setNombre("Juan Pérez");
            profesor1 = profesorService.save(profesor1);

            Profesor profesor2 = new Profesor();
            profesor2.setNombre("María González");
            profesor2 = profesorService.save(profesor2);

            // Crear aulas
            Aula aula1 = new Aula();
            aula1.setNombre("Aula 101");
            aula1.setProfesor(profesor1);  // Asignar profesor a aula
            aulaService.save(aula1);

            Aula aula2 = new Aula();
            aula2.setNombre("Aula 102");
            aula2.setProfesor(profesor2);  // Asignar profesor a aula
            aulaService.save(aula2);

            // Crear direcciones
            Direccion direccion1 = new Direccion();
            direccion1.setCalle("Calle Falsa 123");
            direccion1 = direccionService.save(direccion1);

            Direccion direccion2 = new Direccion();
            direccion2.setCalle("Avenida Siempre Viva 742");
            direccion2 = direccionService.save(direccion2);

            // Crear estudiantes
            Estudiante estudiante1 = new Estudiante();
            estudiante1.setNombre("Carlos López");
            estudiante1.setDireccion(direccion1);  // Asignar dirección al estudiante
            estudiante1 = estudianteService.save(estudiante1);

            Estudiante estudiante2 = new Estudiante();
            estudiante2.setNombre("Ana Martínez");
            estudiante2.setDireccion(direccion2);  // Asignar dirección al estudiante
            estudiante2 = estudianteService.save(estudiante2);

            // Crear cursos
            Curso curso1 = new Curso();
            curso1.setNombre("Matemáticas");
            curso1.setProfesor(profesor1);  // Asignar profesor al curso
            curso1 = cursoService.save(curso1);

            Curso curso2 = new Curso();
            curso2.setNombre("Física");
            curso2.setProfesor(profesor2);  // Asignar profesor al curso
            curso2 = cursoService.save(curso2);

            // Asignar estudiantes a cursos
            estudiante1.getCursos().add(curso1);
            estudiante2.getCursos().add(curso2);

            estudianteService.save(estudiante1);
            estudianteService.save(estudiante2);

            logger.info("Data initialization completed successfully.");
        } catch (Exception e) {
            logger.error("Error during data initialization", e);
            throw e;
        }
    }
}