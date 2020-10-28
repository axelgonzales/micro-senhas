package pe.senhas.co.core.cocosenhas.service;

import java.util.List;

import pe.senhas.co.core.cocosenhas.domain.AudioEntity;
import pe.senhas.co.core.cocosenhas.domain.ConversationEntity;
import pe.senhas.co.core.cocosenhas.model.ConversationRequest;

public interface ConversationService {

	ConversationEntity crear(ConversationRequest request);
	List<AudioEntity> obtenerAudios(Integer  ConversationId);
}
