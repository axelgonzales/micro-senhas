package pe.senhas.co.core.cocosenhas.service;

import pe.senhas.co.core.cocosenhas.domain.QualificationEntity;
import pe.senhas.co.core.cocosenhas.model.QualificationRequest;

import java.util.List;
import java.util.Optional;

public interface QualificationService {

    public List<QualificationEntity> findAllQualifications();

    public Optional<QualificationEntity> findQualificationById(Long id);

    public QualificationEntity saveQualification(QualificationRequest qualificationRequest);

}
