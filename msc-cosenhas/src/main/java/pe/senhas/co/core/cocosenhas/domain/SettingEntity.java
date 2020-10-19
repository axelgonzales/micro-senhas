package pe.senhas.co.core.cocosenhas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettingEntity {

    @Id
    @SequenceGenerator(name = "setting_id_generator", sequenceName = "setting_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "setting_id_generator")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Text cannot be empty")
    private String cod;
    

    @Column(nullable = false)
    private String value;
    
    
    @Column(nullable = false)
    private Integer valueInt;
    
    @Column(nullable = false)
    private Integer valueDouble;
}
