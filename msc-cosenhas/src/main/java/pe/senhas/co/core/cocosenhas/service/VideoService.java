package pe.senhas.co.core.cocosenhas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import pe.senhas.co.core.cocosenhas.domain.VideoEntity;
import pe.senhas.co.core.cocosenhas.model.VideoRequest;
import pe.senhas.co.core.cocosenhas.model.VideoSearch;

public interface VideoService {

    public List<VideoEntity> findAllVideos();

    public Optional<VideoEntity> findVideoById(Long id);

    public VideoEntity saveVideo(VideoRequest videoRequest, MultipartFile multipart);

    public VideoEntity updateVideo(VideoRequest videoRequest, Long id);

    public void deleteVideoById(Long id);

	public List<VideoRequest> searchVideo(VideoSearch search);
}
