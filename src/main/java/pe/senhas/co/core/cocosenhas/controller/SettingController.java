package pe.senhas.co.core.cocosenhas.controller;

import pe.senhas.co.core.cocosenhas.domain.SettingEntity;
import pe.senhas.co.core.cocosenhas.service.SettingService;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.SettingResponse;
import pe.senhas.co.core.cocosenhas.model.SettingRequest;
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
@RequestMapping("v1/setting")
@Api(value = "SettingController", produces = "application/json", tags = { "Controlador Setting" })
public class SettingController {

    private final SettingService settingService;

    @Autowired
    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping
    public List<SettingEntity> getAllSettings() {
        return settingService.findAllSettings();
    }

    @ApiOperation(value = "Obtiene Setting por ID", tags = { "Controlador Setting" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Setting encontrada", response = SettingEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<SettingEntity> getSettingById(@PathVariable Long id) {
        return settingService.findSettingById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra Setting", tags = { "Controlador Setting" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Setting registrada", response = SettingRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<SettingResponse> createSetting(@RequestBody @Validated SettingRequest settingRequest) {
        settingService.saveSetting(settingRequest);
        return new ResponseEntity<>(new SettingResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Setting", tags = { "Controlador Setting" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Setting actualizada", response = SettingRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<SettingResponse> updateSetting(@PathVariable Long id, @RequestBody SettingRequest settingRequest) throws Exception {
        settingService.updateSetting(settingRequest, id);
        return new ResponseEntity<>(new SettingResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina Setting", tags = { "Controlador Setting" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Setting eliminada", response = SettingRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<SettingResponse> deleteSetting(@PathVariable Long id) {
        settingService.deleteSettingById(id);
        return new ResponseEntity<>(new SettingResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
