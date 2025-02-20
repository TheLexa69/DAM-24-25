package demoapp.controller;

import demoapp.model.entity.Direccion;
import demoapp.model.entity.Estudiante;
import demoapp.model.entity.Curso;
import demoapp.service.DireccionService;
import demoapp.service.EstudianteService;
import demoapp.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private final EstudianteService estudianteService;

    @Autowired
    private final CursoService cursoService;

    @Autowired
    private DireccionService direccionService;

    public EstudianteController(EstudianteService estudianteService, CursoService cursoService, DireccionService direccionService) {
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
        this.direccionService = direccionService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", estudianteService.listar());
        return "estudiantes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("cursos", cursoService.listar());
        return "formulario_estudiante";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante) {
        if (estudiante.getDireccion() != null) {
            direccionService.save(estudiante.getDireccion());
        }
        estudianteService.crear(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        if (estudiante != null) {
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("cursos", cursoService.listar());
            return "formulario_estudiante";
        }
        return "redirect:/estudiantes";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Estudiante estudiante) {
        Direccion direccion = estudiante.getDireccion();
        if (direccion != null) {
            direccion = direccionService.save(direccion);
            estudiante.setDireccion(direccion);
        }

        estudianteService.actualizar(id, estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "redirect:/estudiantes";
    }
}