package demoapp.config;

import demoapp.model.entity.Image;
import demoapp.model.repository.ImageRepository;
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

    @PostConstruct
    public void initializeData() {
        if (imageRepository.count() == 0) {
            List<Image> images = Arrays.asList(
                    new Image("/images/ironman.jpg", 0),
                    new Image("/images/jocker.jpg", 0),
                    new Image("/images/spiderman.webp", 0)
            );
            imageRepository.saveAll(images);
        }
    }
}
