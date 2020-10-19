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
@Table(name="videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity {

    @Id
    @SequenceGenerator(name = "video_id_generator", sequenceName = "video_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "video_id_generator")
    private Long id;
    
    @Column(nullable = false)
    private Long usuarioId;
    
    private Long stateId;
    
    private String route;
    
    private String path;

    private Long levelId;
    
    private String alternatives1;
    
    private String alternatives2;
    
    private String alternatives3;
    
    private String alternatives4;
    
    
    private int alternativeCorrect;
    
    private Long approves;
    
    private Long disapproves;
}
