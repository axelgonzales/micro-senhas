package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.UserEntity;
import pe.senhas.co.core.cocosenhas.model.UserRequest;

public class UserDTOToUserEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public UserEntity userDTOToUserEntityMapper(UserRequest userRequest) {
        return modelMapper.map(userRequest, UserEntity.class);
    }
}