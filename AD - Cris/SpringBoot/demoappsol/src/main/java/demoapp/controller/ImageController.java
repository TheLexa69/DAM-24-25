package demoapp.controller;

import demoapp.model.entity.Image;
import demoapp.model.entity.PersonForm;
import demoapp.model.repository.PersonFormRepository;
import demoapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private PersonFormRepository personFormRepository;

    @GetMapping("/images")
    public String showImages(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "email", required = false) String email, Model model) {
        List<Image> images = imageService.getAllImages();
        model.addAttribute("personName", name);
        model.addAttribute("personEmail", email);
        model.addAttribute("images", images);
        return "images";
    }


    @PostMapping("/vote")
    public String vote(@RequestParam int index, @RequestParam String personName, @RequestParam String personEmail, @RequestParam String ipAddress) {
        try {
            // Incrementar el voto de la imagen
            Image image = imageService.incrementLikes(index);

            // Verificar si ya existe un registro para esta persona
            Optional<PersonForm> existingPerson = personFormRepository.findByEmail(personEmail);

            if (existingPerson.isPresent()) {
                // Si ya existe, actualizar su información si es necesario
                PersonForm personForm = existingPerson.get();
                personForm.setVotado(true);
                personForm.setVotedImage(image);
                personForm.setVoteDateTime(LocalDateTime.now());
                personForm.setRole("default");
                personFormRepository.save(personForm);
            } else {
                // Si no existe, crear un nuevo registro
                PersonForm personForm = new PersonForm();
                personForm.setName(personName);
                personForm.setEmail(personEmail);
                personForm.setVotado(true);
                personForm.setVotedImage(image);
                personForm.setAge(18); // Valor por defecto
                personForm.setVoteDateTime(LocalDateTime.now());
                personForm.setRole("default");
                personFormRepository.save(personForm);
            }

            // Redirigir a la página de agradecimiento
            return "redirect:/thankyou";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    @GetMapping("/thankyou")
    public String thankYou() {
        return "thankyou";
    }

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }
}