package demoapp.controller;

import demoapp.model.entity.PersonForm;
import demoapp.model.repository.PersonFormRepository;
import demoapp.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Controller
public class WebController implements WebMvcConfigurer {
    @Autowired
    private PersonFormRepository personFormRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm, Model model) {
        model.addAttribute("persons", personFormRepository.findAll());
        System.out.println("Persons: " + personFormRepository.findAll());
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        Optional<PersonForm> existingPerson = personFormRepository.findByEmail(personForm.getEmail());
        if (existingPerson.isPresent()) {
            if (!existingPerson.get().isVotado()) {
                return "redirect:/images?name=" + existingPerson.get().getName() + "&email=" + existingPerson.get().getEmail();
            }
            model.addAttribute("emailExists", true);
            return "form";
        }

        personForm.setVotado(false); // Set votado to false
        personForm.setRole("default"); // Set role to default
        personFormRepository.save(personForm); // Save to the database
        return "redirect:/images?name=" + personForm.getName() + "&email=" + personForm.getEmail();
    }
}