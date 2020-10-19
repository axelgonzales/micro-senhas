package pe.senhas.co.core.cocosenhas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="parameters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterEntity {

    @Id
    @SequenceGenerator(name = "parameter_id_generator", sequenceName = "parameter_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "parameter_id_generator")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "codTabla cannot be empty")
    private String codTabla;
    
    @Column(nullable = false)
    @NotEmpty(message = "correlative cannot be empty")
    private String correlative;
    
    @Column(nullable = true)
    private String valueString;
    
    @Column(nullable = true)
    private Integer valueInt;
    
    @Column(nullable = true)
    private Double valueDouble;
    
}
