package pe.senhas.co.core.cocosenhas.service.impl;

import pe.senhas.co.core.cocosenhas.domain.VideoEntity;
import pe.senhas.co.core.cocosenhas.repository.VideoRepository;
import pe.senhas.co.core.cocosenhas.model.FileAws;
import pe.senhas.co.core.cocosenhas.model.VideoRequest;
import pe.senhas.co.core.cocosenhas.model.VideoSearch;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.VideoDTOToVideoEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import pe.senhas.co.core.cocosenhas.service.AmazonService;
import pe.senhas.co.core.cocosenhas.service.VideoService;
import pe.senhas.co.core.cocosenhas.exception.ModelNotFoundException;
import pe.senhas.co.core.cocosenhas.constant.Constant;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final AmazonService amazonService;
    private VideoDTOToVideoEntityMapper videoDTOToVideoEntityMapper = new VideoDTOToVideoEntityMapper();


    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository,AmazonService amazonService) {
        this.videoRepository = videoRepository;
        this.amazonService = amazonService;
    }

    public List<VideoEntity> findAllVideos() {

        List<VideoEntity> listVideo = videoRepository.findAll();

        log.info("GET ALL Video MESSAGE TEST" );
        return listVideo;
    }

    public Optional<VideoEntity> findVideoById(Long id) {

        Optional<VideoEntity> optionalVideo = videoRepository.findById(id);

        log.info("GET Video/ MESSAGE TEST" );
        
        return optionalVideo;
    }

    public VideoEntity saveVideo(VideoRequest videoRequest, MultipartFile multipart ) {
    	System.out.println("match");
    	VideoEntity videoEntity = videoDTOToVideoEntityMapper.videoDTOToVideoEntityMapper(videoRequest);
    	System.out.println("match");
    	FileAws fileAWS = amazonService.uploadFile(multipart, false);
    	videoEntity.setPath(fileAWS.getPath());
    	videoEntity.setRoute(fileAWS.getBucket());
    	videoEntity.setStateId(1L);
    	System.out.println("video " + videoEntity.toString());
    	try {
            videoEntity = videoRepository.save(videoEntity);

		} catch (Exception e) {
			System.out.println("error " + e.getLocalizedMessage());
		}
        log.info("POST Video MESSAGE TEST" );
        
        return videoEntity;
    }

    public VideoEntity updateVideo(VideoRequest videoRequest, Long id) {

        return videoRepository.findById(id).map(videoRequestObje -> {
        	VideoEntity video = videoDTOToVideoEntityMapper.videoDTOToVideoEntityMapper(videoRequest);
        	video.setId(id);
            video = videoRepository.save(video);
            log.info("UPDATE Video MESSAGE TEST" );
            
        return video;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteVideoById(Long id) {

        videoRepository.findById(id).ifPresent(delete -> videoRepository.deleteById(id));

        log.info("DELETE Video/ MESSAGE TEST" );
        
    }

	@Override
	public List<VideoRequest> searchVideo(VideoSearch search) {
		List<VideoEntity> lstVideo = videoRepository.findByStateId(search.getStateId());
		List<VideoRequest> lstVideoReq = new ArrayList<>(); 
		for (VideoEntity videoEntity : lstVideo) {
			VideoRequest videoRequest = videoDTOToVideoEntityMapper.videoEntitToVideoDTOyMapper(videoEntity);
			videoRequest.setUrl(amazonService.getFileUrl( videoEntity.getPath()));
			lstVideoReq.add(videoRequest);
		}
		
		return lstVideoReq;
	}
}