package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.SettingEntity;
import pe.senhas.co.core.cocosenhas.model.SettingRequest;

public class SettingDTOToSettingEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public SettingEntity settingDTOToSettingEntityMapper(SettingRequest settingRequest) {
        return modelMapper.map(settingRequest, SettingEntity.class);
    }
}