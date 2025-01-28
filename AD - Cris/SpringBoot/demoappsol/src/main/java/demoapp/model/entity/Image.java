package demoapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private int votes;

    public Image() {
    }

    public Image(String url, int votes) {
        this.url = url;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", votes=" + votes +
//                ", votedImage=" + votedImage +
                '}';
    }
}