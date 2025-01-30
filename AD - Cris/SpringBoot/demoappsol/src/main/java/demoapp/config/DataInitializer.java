package demoapp.config;

import demoapp.model.entity.Image;
import demoapp.model.entity.PersonForm;
import demoapp.model.repository.ImageRepository;
import demoapp.model.repository.PersonFormRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PersonFormRepository personFormRepository;

    @PostConstruct
    public void initializeData() {
        if (imageRepository.count() == 0) {
            try{
                System.out.println("Initializing images data...");
                List<Image> images = Arrays.asList(
                        new Image("/images/ironman.jpg", 1),
                        new Image("/images/jocker.jpg", 0),
                        new Image("/images/spiderman.webp", 1)
                );
                imageRepository.saveAll(images);
                System.out.println("Images data initialized successfully.");
            } catch (Exception e) {
                System.out.println("Error initializing images data: " + e.getMessage());
            }
        }

        if (personFormRepository.count() == 0) {
            try {
                System.out.println("Initializing persons data...");
                List<Image> images = imageRepository.findAll();
                if (images.size() >= 3) {
                    List<PersonForm> persons = Arrays.asList(
                            new PersonForm("Sofia", 20, "sofia@gmail.com", "192.168.4.18", true, images.get(0)),
                            new PersonForm("Carlos", 25, "carlos@gmail.com", "192.168.4.19", false),
                            new PersonForm("Maria", 30, "maria@gmail.com", "192.168.4.20", true, images.get(2))
                    );
                    personFormRepository.saveAll(persons);
                    System.out.println("Persons data initialized successfully.");
                } else {
                    System.out.println("Error initializing persons data: Not enough images.");
                }
            } catch (Exception e) {
                System.out.println("Error initializing persons data: " + e.getMessage());
            }
        }
    }
}
