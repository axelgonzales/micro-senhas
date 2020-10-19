package pe.senhas.co.core.cocosenhas.service.impl.mapper;

import org.modelmapper.ModelMapper;
import pe.senhas.co.core.cocosenhas.domain.PersonEntity;
import pe.senhas.co.core.cocosenhas.model.PersonRequest;

public class PersonDTOToPersonEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public PersonEntity personDTOToPersonEntityMapper(PersonRequest personRequest) {
        return modelMapper.map(personRequest, PersonEntity.class);
    }
}