package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.ParameterEntity;
import pe.senhas.co.core.cocosenhas.model.ParameterRequest;

public class ParameterDTOToParameterEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public ParameterEntity parameterDTOToParameterEntityMapper(ParameterRequest parameterRequest) {
        return modelMapper.map(parameterRequest, ParameterEntity.class);
    }
}