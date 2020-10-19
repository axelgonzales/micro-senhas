package pe.senhas.co.core.cocosenhas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest {

	private Long id;
    
    private Long usuarioId;

    private Long levelId;
    
    private String alternatives1;
    
    private String alternatives2;
    
    private String alternatives3;
    
    private String alternatives4;
    
    
    private int alternativeCorrect;
    
    private Long likes;
    
    private Long dislikes;
    
    private Long disapproves;
    
    private String url;
    
    
}
