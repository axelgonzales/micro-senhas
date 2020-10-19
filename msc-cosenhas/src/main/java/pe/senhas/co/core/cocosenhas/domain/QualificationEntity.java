package pe.senhas.co.core.cocosenhas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="qualifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationEntity {

    @Id
    @SequenceGenerator(name = "qualification_id_generator", sequenceName = "qualification_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "qualification_id_generator")
    private Long id;

    @Column(nullable = false)
    private Long idVideo;
    
    @Column(nullable = false)
    private Long idUser;
    
    @Column(nullable = false)
    private Long idQualification;
    
    @Column(nullable = false)
    private Long idReason;
    
    
}
