package demoapp.controller;

import demoapp.model.entity.Profesor;
import demoapp.service.ProfesorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("profesores", profesorService.listar());
        return "profesores";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "formulario_profesor";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Profesor profesor) {
        profesorService.save(profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Profesor profesor = profesorService.obtenerPorId(id);
        if (profesor != null) {
            model.addAttribute("profesor", profesor);
            return "formulario_profesor";
        }
        return "redirect:/profesores";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Profesor profesor) {
        profesorService.actualizar(id, profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        profesorService.eliminar(id);
        return "redirect:/profesores";
    }
}