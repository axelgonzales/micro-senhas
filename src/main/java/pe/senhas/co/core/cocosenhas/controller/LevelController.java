package pe.senhas.co.core.cocosenhas.controller;

import java.util.List;

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
import pe.senhas.co.core.cocosenhas.constant.Constant;
import pe.senhas.co.core.cocosenhas.domain.LevelEntity;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.LevelRequest;
import pe.senhas.co.core.cocosenhas.model.LevelResponse;
import pe.senhas.co.core.cocosenhas.model.LevelSearch;
import pe.senhas.co.core.cocosenhas.service.LevelService;

@RestController
@RequestMapping("v1/level")
@Api(value = "LevelController", produces = "application/json", tags = { "Controlador Level" })
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public List<LevelEntity> getAllLevels() {
        return levelService.findAllLevels();
    }

    @ApiOperation(value = "Obtiene Level por ID", tags = { "Controlador Level" })
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Level encontrada", response = LevelEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<List<LevelEntity>> getLevelById(LevelSearch  search) {
        return new ResponseEntity<>(levelService.findLevelSearch(search),HttpStatus.OK);
    }

    @ApiOperation(value = "Registra Level", tags = { "Controlador Level" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Level registrada", response = LevelRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<LevelResponse> createLevel(@RequestBody @Validated LevelRequest levelRequest) {
        levelService.saveLevel(levelRequest);
        return new ResponseEntity<>(new LevelResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Level", tags = { "Controlador Level" })
    @PutMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Level actualizada", response = LevelRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<LevelResponse> updateLevel(@PathVariable Long id, @RequestBody LevelRequest levelRequest) throws Exception {
        levelService.updateLevel(levelRequest);
        return new ResponseEntity<>(new LevelResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina Level", tags = { "Controlador Level" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Level eliminada", response = LevelRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<LevelResponse> deleteLevel(@PathVariable Long id) {
        levelService.deleteLevelById(id);
        return new ResponseEntity<>(new LevelResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
