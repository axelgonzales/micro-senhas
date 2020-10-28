package pe.senhas.co.core.cocosenhas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="conversartion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationEntity {

	@SequenceGenerator(name = "conversation_id_generator", sequenceName = "conversation_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "conversation_id_generator")
    private Long id;
	
	private String name;
	
    private String reaseon;
    
    private Long userId;
}
