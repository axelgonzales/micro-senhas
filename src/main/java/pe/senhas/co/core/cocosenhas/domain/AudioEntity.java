package pe.senhas.co.core.cocosenhas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="audio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioEntity {


	@SequenceGenerator(name = "audio_id_generator", sequenceName = "audio_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "audio_id_generator")
    private Long id;
	
	private String conversation;
	
	private String bucket;
	
	private String key;
	
	
	
}
