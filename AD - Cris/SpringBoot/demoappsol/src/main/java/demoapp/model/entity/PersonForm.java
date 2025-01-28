package demoapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person_form", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class PersonForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 30)
    private String name;

    @Min(18)
    private Integer age;

    @Email(message = "El email debe ser una dirección de correo válida!")
    private String email;

    private String ipAddress;

    private boolean votado = false;

    @ManyToOne
    @JoinColumn(name = "image_id", foreignKey = @ForeignKey(name = "FK_IMAGE_ID"))
    private Image votedImage;

    @Override
    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}