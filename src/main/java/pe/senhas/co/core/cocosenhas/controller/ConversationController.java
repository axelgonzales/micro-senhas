package pe.senhas.co.core.cocosenhas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pe.senhas.co.core.cocosenhas.domain.AudioEntity;
import pe.senhas.co.core.cocosenhas.domain.ConversationEntity;
import pe.senhas.co.core.cocosenhas.domain.LevelEntity;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.ConversationRequest;
import pe.senhas.co.core.cocosenhas.service.ConversationService;

@RestController
@RequestMapping("v1/conversation")
@Api(value = "ConversationController", produces = "application/json", tags = { "Controlador Level" })
public class ConversationController {

	private final ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping
    public List<AudioEntity> getAllLevels(Integer conversationId) {
        return conversationService.obtenerAudios(conversationId);
    }

    @ApiOperation(value = "Obtiene Level por ID", tags = { "Controlador Level" })
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Level encontrada", response = LevelEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ConversationEntity> crearConversation(ConversationRequest  request) {
        return new ResponseEntity<>(conversationService.crear(request),HttpStatus.OK);
    }
    
}
