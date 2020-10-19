package pe.senhas.co.core.cocosenhas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettingRequest {
    private Long id;
    private String cod;
    private String value;
    private Integer valueInt;
    private Integer Double;
}
