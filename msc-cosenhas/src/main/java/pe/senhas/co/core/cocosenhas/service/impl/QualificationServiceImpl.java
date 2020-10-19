package pe.senhas.co.core.cocosenhas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.senhas.co.core.cocosenhas.constant.Constant;
import pe.senhas.co.core.cocosenhas.domain.QualificationEntity;
import pe.senhas.co.core.cocosenhas.domain.VideoEntity;
import pe.senhas.co.core.cocosenhas.exception.ModelNotFoundException;
import pe.senhas.co.core.cocosenhas.model.QualificationRequest;
import pe.senhas.co.core.cocosenhas.repository.QualificationRepository;
import pe.senhas.co.core.cocosenhas.repository.VideoRepository;
import pe.senhas.co.core.cocosenhas.service.QualificationService;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.QualificationDTOToQualificationEntityMapper;

@Slf4j
@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;
    private final VideoRepository videoRepository;
    private QualificationDTOToQualificationEntityMapper qualificationDTOToQualificationEntityMapper = new QualificationDTOToQualificationEntityMapper();


    @Autowired
    public QualificationServiceImpl(VideoRepository videoRepository,QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
        this.videoRepository = videoRepository;
    }

    public List<QualificationEntity> findAllQualifications() {

        List<QualificationEntity> listQualification = qualificationRepository.findAll();

        log.info("GET ALL Qualification MESSAGE TEST" );
        return listQualification;
    }

    public Optional<QualificationEntity> findQualificationById(Long id) {

        Optional<QualificationEntity> optionalQualification = qualificationRepository.findById(id);

        log.info("GET Qualification/ MESSAGE TEST" );
        
        return optionalQualification;
    }	

    public QualificationEntity saveQualification(QualificationRequest qualificationRequest) {

        QualificationEntity qualificationEntity = qualificationRepository.save(qualificationDTOToQualificationEntityMapper.qualificationDTOToQualificationEntityMapper(qualificationRequest));
        Optional<VideoEntity> optionalVideo = videoRepository.findById(qualificationRequest.getIdVideo());
        if (optionalVideo.isPresent()) {
        	VideoEntity videoEntity= optionalVideo.get();
        	if (qualificationRequest.getIdQualification()== 1) {
        		videoEntity.setApproves(videoEntity.getApproves()+1);
			}else {
				videoEntity.setDisapproves(videoEntity.getDisapproves() +1);
			}
        	videoRepository.save(videoEntity);
		}else {
			throw new ModelNotFoundException(Constant.PERSONA_NOT_FOUND);
		}
        
        log.info("POST Qualification MESSAGE TEST" );
        
        return qualificationEntity;
    }

}