package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.VideoEntity;
import pe.senhas.co.core.cocosenhas.model.VideoRequest;

public class VideoDTOToVideoEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public VideoEntity videoDTOToVideoEntityMapper(VideoRequest videoRequest) {
        return modelMapper.map(videoRequest, VideoEntity.class);
    }
    
    public VideoRequest videoEntitToVideoDTOyMapper(VideoEntity videoEntity) {
        return modelMapper.map(videoEntity, VideoRequest.class);
    }
}