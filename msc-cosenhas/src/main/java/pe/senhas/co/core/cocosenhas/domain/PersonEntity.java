package pe.senhas.co.core.cocosenhas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @SequenceGenerator(name = "person_id_generator", sequenceName = "person_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "person_id_generator")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Text cannot be empty")
    private String text;
}
