package demoapp.service;

import demoapp.model.entity.Image;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private List<Image> images = new ArrayList<>();

    public ImageService() {
        images.add(new Image("/images/ironman.jpg", 0));
        images.add(new Image("/images/jocker.jpg", 0));
        images.add(new Image("/images/spiderman.webp", 0));
    }

    public List<Image> getAllImages() {
        return images;
    }

    public void incrementLikes(int index) {
        Image image = images.get(index);
        image.setVotes(image.getVotes() + 1);
    }
}