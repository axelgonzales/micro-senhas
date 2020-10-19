package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.LevelEntity;
import pe.senhas.co.core.cocosenhas.model.LevelRequest;

public class LevelDTOToLevelEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public LevelEntity levelDTOToLevelEntityMapper(LevelRequest levelRequest) {
        return modelMapper.map(levelRequest, LevelEntity.class);
    }
}