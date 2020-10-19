package pe.senhas.co.core.cocosenhas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterRequest {

    
    private String codTabla;

    private String correlative;

    private String valueString;    
   
    private Integer valueInt;
    
   
    private Double valueDouble;
}
