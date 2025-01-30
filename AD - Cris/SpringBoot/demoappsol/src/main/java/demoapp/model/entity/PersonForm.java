package demoapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PersonForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotNull(message = "El nombre no puede ser nulo!")
    @Size(min=2, max=30)
    private String name;

//    @NotNull(message = "La edad no puede ser nula!")
    @Min(18)
    private Integer age;

//    @NotNull(message = "El email no puede ser nulo!")
    @Email(message = "El email debe ser una dirección de correo válida!")
    private String email;

    private String ipAddress;

    private boolean votado = false;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image votedImage;

    public PersonForm() {
    }

    public PersonForm(String name, Integer age, String email, String ipAddress, boolean votado, Image votedImage) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.ipAddress = ipAddress;
        this.votado = votado;
        this.votedImage = votedImage;
    }

    public PersonForm(String name, Integer age, String email, String ipAddress, boolean votado) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.ipAddress = ipAddress;
        this.votado = votado;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", votado=" + votado +
                ", votedImage=" + votedImage +
                '}';
    }
}