package pe.senhas.co.core.cocosenhas.service;

import pe.senhas.co.core.cocosenhas.domain.ParameterEntity;
import pe.senhas.co.core.cocosenhas.model.ParameterRequest;

import java.util.List;
import java.util.Optional;

public interface ParameterService {

    public List<ParameterEntity> findAllParameters();

    public Optional<ParameterEntity> findParameterById(Long id);

    public ParameterEntity saveParameter(ParameterRequest parameterRequest);

    public ParameterEntity updateParameter(ParameterRequest parameterRequest, Long id);

    public void deleteParameterById(Long id);

	public List<ParameterEntity> findParameterByCodTabla(String codTabla);
}
