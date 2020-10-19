package pe.senhas.co.core.cocosenhas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationRequest {

    private Long idVideo;
    
    private Long idUser;
    
    private Long idQualification;
    
    private Long idReason;
}
