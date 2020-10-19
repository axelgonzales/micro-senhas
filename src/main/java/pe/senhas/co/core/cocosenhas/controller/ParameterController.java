package pe.senhas.co.core.cocosenhas.controller;

import pe.senhas.co.core.cocosenhas.domain.ParameterEntity;
import pe.senhas.co.core.cocosenhas.service.ParameterService;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.ParameterResponse;
import pe.senhas.co.core.cocosenhas.model.ParameterRequest;
import pe.senhas.co.core.cocosenhas.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("v1/parameter")
@Api(value = "ParameterController", produces = "application/json", tags = { "Controlador Parameter" })
public class ParameterController {

    private final ParameterService parameterService;

    @Autowired
    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping
    public List<ParameterEntity> getAllParameters() {
        return parameterService.findAllParameters();
    }

    @ApiOperation(value = "Obtiene Parameter por ID", tags = { "Controlador Parameter" })
    @GetMapping("codTabla/{codTabla}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Parameter encontrada", response = ParameterEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<List<ParameterEntity>> getParameterById(@PathVariable String codTabla) {
        return new ResponseEntity<>(parameterService.findParameterByCodTabla(codTabla),HttpStatus.OK);
    }

    @ApiOperation(value = "Registra Parameter", tags = { "Controlador Parameter" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Parameter registrada", response = ParameterRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ParameterResponse> createParameter(@RequestBody @Validated ParameterRequest parameterRequest) {
        parameterService.saveParameter(parameterRequest);
        return new ResponseEntity<>(new ParameterResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Parameter", tags = { "Controlador Parameter" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Parameter actualizada", response = ParameterRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ParameterResponse> updateParameter(@PathVariable Long id, @RequestBody ParameterRequest parameterRequest) throws Exception {
        parameterService.updateParameter(parameterRequest, id);
        return new ResponseEntity<>(new ParameterResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina Parameter", tags = { "Controlador Parameter" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Parameter eliminada", response = ParameterRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ParameterResponse> deleteParameter(@PathVariable Long id) {
        parameterService.deleteParameterById(id);
        return new ResponseEntity<>(new ParameterResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
