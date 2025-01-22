package demoapp.controller;

import demoapp.model.entity.Image;
import demoapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/images")
    public String showImages(Model model) {
        List<Image> images = imageService.getAllImages();
        model.addAttribute("images", images);
        return "images";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam int index) {
        imageService.incrementLikes(index);
        return "redirect:/images";
    }
}