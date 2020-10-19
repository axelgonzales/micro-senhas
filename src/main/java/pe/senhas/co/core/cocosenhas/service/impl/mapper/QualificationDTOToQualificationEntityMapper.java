package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.QualificationEntity;
import pe.senhas.co.core.cocosenhas.model.QualificationRequest;

public class QualificationDTOToQualificationEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public QualificationEntity qualificationDTOToQualificationEntityMapper(QualificationRequest qualificationRequest) {
        return modelMapper.map(qualificationRequest, QualificationEntity.class);
    }
}