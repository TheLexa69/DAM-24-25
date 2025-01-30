package demoapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private int votes;

//    @OneToOne
//    @JoinColumn(name = "image_id")
//    private Image votedImage;

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