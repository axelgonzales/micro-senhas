package pe.senhas.co.core.cocosenhas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pe.senhas.co.core.cocosenhas.constant.Constant;
import pe.senhas.co.core.cocosenhas.domain.QualificationEntity;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.QualificationRequest;
import pe.senhas.co.core.cocosenhas.model.QualificationResponse;
import pe.senhas.co.core.cocosenhas.service.QualificationService;

@RestController
@RequestMapping("v1/qualification")
@Api(value = "QualificationController", produces = "application/json", tags = { "Controlador Qualification" })
public class QualificationController {

    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping
    public List<QualificationEntity> getAllQualifications() {
        return qualificationService.findAllQualifications();
    }

    @ApiOperation(value = "Obtiene Qualification por ID", tags = { "Controlador Qualification" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Qualification encontrada", response = QualificationEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<QualificationEntity> getQualificationById(@PathVariable Long id) {
        return qualificationService.findQualificationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra Qualification", tags = { "Controlador Qualification" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Qualification registrada", response = QualificationRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<QualificationResponse> createQualification(@RequestBody @Validated QualificationRequest qualificationRequest) {
        qualificationService.saveQualification(qualificationRequest);
        return new ResponseEntity<>(new QualificationResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

}
