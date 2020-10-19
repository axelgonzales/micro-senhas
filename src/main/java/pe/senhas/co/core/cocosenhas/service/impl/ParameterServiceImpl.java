package pe.senhas.co.core.cocosenhas.service.impl;

import pe.senhas.co.core.cocosenhas.domain.ParameterEntity;
import pe.senhas.co.core.cocosenhas.repository.ParameterRepository;
import pe.senhas.co.core.cocosenhas.model.ParameterRequest;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.ParameterDTOToParameterEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.senhas.co.core.cocosenhas.service.ParameterService;
import pe.senhas.co.core.cocosenhas.exception.ModelNotFoundException;
import pe.senhas.co.core.cocosenhas.constant.Constant;


import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {

    private final ParameterRepository parameterRepository;
    private ParameterDTOToParameterEntityMapper parameterDTOToParameterEntityMapper = new ParameterDTOToParameterEntityMapper();


    @Autowired
    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    public List<ParameterEntity> findAllParameters() {

        List<ParameterEntity> listParameter = parameterRepository.findAll();

        log.info("GET ALL Parameter MESSAGE TEST" );
        return listParameter;
    }

    public Optional<ParameterEntity> findParameterById(Long id) {

        Optional<ParameterEntity> optionalParameter = parameterRepository.findById(id);

        log.info("GET Parameter/ MESSAGE TEST" );
        
        return optionalParameter;
    }

    public ParameterEntity saveParameter(ParameterRequest parameterRequest) {

        ParameterEntity parameterEntity = parameterRepository.save(parameterDTOToParameterEntityMapper.parameterDTOToParameterEntityMapper(parameterRequest));

        log.info("POST Parameter MESSAGE TEST" );
        
        return parameterEntity;
    }

    public ParameterEntity updateParameter(ParameterRequest parameterRequest, Long id) {

        return parameterRepository.findById(id).map(parameterRequestObje -> {
            ParameterEntity entity = parameterDTOToParameterEntityMapper.parameterDTOToParameterEntityMapper(parameterRequest);
            entity.setId(id);
            ParameterEntity parameter = parameterRepository.save(entity);
            log.info("UPDATE Parameter MESSAGE TEST" );
            
        return parameter;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteParameterById(Long id) {

        parameterRepository.findById(id).ifPresent(delete -> parameterRepository.deleteById(id));

        log.info("DELETE Parameter/ MESSAGE TEST" );
        
    }

	@Override
	public List<ParameterEntity> findParameterByCodTabla(String codTabla) {
		return parameterRepository.findByCodTabla(codTabla);
	}
}