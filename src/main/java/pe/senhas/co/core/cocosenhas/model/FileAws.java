package pe.senhas.co.core.cocosenhas.model;

import lombok.Data;

@Data
public class FileAws {

	private String bucket;
	private String url;
	private String path;
	private Boolean bPulic;
	
	
}
