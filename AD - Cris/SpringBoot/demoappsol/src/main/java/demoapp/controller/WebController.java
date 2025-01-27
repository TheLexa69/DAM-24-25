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
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        personForm.setVotado(false); // Set votado to true
        personFormRepository.save(personForm); // Save to the database
        return "redirect:/images?name=" + personForm.getName();
    }
}