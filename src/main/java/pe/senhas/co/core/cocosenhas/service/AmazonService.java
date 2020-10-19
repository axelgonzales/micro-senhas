package pe.senhas.co.core.cocosenhas.service;

import org.springframework.web.multipart.MultipartFile;

import pe.senhas.co.core.cocosenhas.model.FileAws;

public interface AmazonService {

	public FileAws uploadFile(MultipartFile file, Boolean bPublic);
	
	public String getFileUrl(String path);
}
