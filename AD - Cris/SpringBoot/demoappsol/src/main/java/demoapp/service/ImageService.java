package demoapp.service;

import demoapp.model.entity.Image;
import demoapp.model.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageService() {
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image incrementLikes(int index) {
        Image image = imageRepository.findById((long) index).orElseThrow(() -> new IllegalArgumentException("Invalid image Id:" + index));
        image.setVotes(image.getVotes() + 1);
        return imageRepository.save(image);
    }

}