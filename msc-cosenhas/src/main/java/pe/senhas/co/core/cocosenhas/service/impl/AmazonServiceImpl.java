package pe.senhas.co.core.cocosenhas.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import pe.senhas.co.core.cocosenhas.constant.Constant;
import pe.senhas.co.core.cocosenhas.model.FileAws;
import pe.senhas.co.core.cocosenhas.service.AmazonService;

@Component
public class AmazonServiceImpl implements AmazonService {

	@Value("${aws.bucket}")
	private String bucket;
	
	@Value("${aws.endpointUrl}")
	private String endpointUrl;
	
	@Value("${aws.accessKey}")
	private String accessKey;
	
	@Value("${aws.secretKey}")
	private String secretKey;
	
	@Value("${aws.region}")
	private String region;
	
	private AmazonS3 s3Client;
	
	
	@PostConstruct
	public void inizialiterAWS() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey,this.secretKey);
		s3Client = AmazonS3ClientBuilder
				.standard()
				.withRegion(this.region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
	}
	
	@Override
	public FileAws uploadFile(MultipartFile multipartFile, Boolean bPublic) {
		FileAws fileAws = null;
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileNameS3 = generateFileName(multipartFile);
			
			fileAws = new FileAws();
			fileAws.setBPulic(bPublic);
			fileAws.setBucket(bucket);
			fileAws.setPath(Constant.FOLDER_VIDEO+"/"+fileNameS3);
			
			if (bPublic) {
				s3Client.putObject(new PutObjectRequest(this.bucket, Constant.FOLDER_VIDEO+"/"+fileNameS3, file)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				fileAws.setUrl(getEndPointUrl(Constant.FOLDER_VIDEO,fileNameS3));
			}
			else {
				s3Client.putObject(new PutObjectRequest(this.bucket, Constant.FOLDER_VIDEO+"/"+fileNameS3, file));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return fileAws;
	}

	@Override
	public String getFileUrl(String path) {
		Date expiration =  new Date();
		long expTimeMillis  = expiration.getTime();
		expTimeMillis += 1000*60*60;
		expiration.setTime(expTimeMillis);
		GeneratePresignedUrlRequest generatePresignedUrlRequest =  new GeneratePresignedUrlRequest(this.bucket, path)
				.withMethod(HttpMethod.GET).withExpiration(expiration);
		URL url =  s3Client.generatePresignedUrl(generatePresignedUrlRequest);
		
		return url.toString();
	}
	
	private String getEndPointUrl(String folderVideo, String fileNameS3) {
	
		return this.endpointUrl + "/" + this.bucket + "/" + folderVideo + "/" + fileNameS3 ;
	}

	private String generateFileName(MultipartFile multipartFile) {
		
		return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replaceAll(" ", "-");
	}

	private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
		File convFile = new File(multipartFile.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(multipartFile.getBytes());
		fos.close();
		return convFile;
	}

	

}
