package pe.senhas.co.core.cocosenhas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="levels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelEntity {

	@Id
    @SequenceGenerator(name = "level_id_generator", sequenceName = "level_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "level_id_generator")
    private Long id;
	
    @Column(nullable = false)
    private Long countryId;
    
	
    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false )
    private Long stateId;
    
    @Column(nullable = false)
    private Long difficulty;
    
}
