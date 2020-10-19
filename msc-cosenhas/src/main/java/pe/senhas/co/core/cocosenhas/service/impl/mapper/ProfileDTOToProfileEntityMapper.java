package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.ProfileEntity;
import pe.senhas.co.core.cocosenhas.model.ProfileRequest;

public class ProfileDTOToProfileEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public ProfileEntity profileDTOToProfileEntityMapper(ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileEntity.class);
    }
}