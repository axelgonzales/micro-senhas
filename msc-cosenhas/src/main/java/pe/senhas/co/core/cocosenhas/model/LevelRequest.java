package pe.senhas.co.core.cocosenhas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequest {
	

    private Long countryId;

    private Long categoryId;

    private Long stateId;

    private Long difficulty;
}
