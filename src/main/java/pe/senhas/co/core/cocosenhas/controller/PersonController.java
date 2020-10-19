package pe.senhas.co.core.cocosenhas.controller;

import pe.senhas.co.core.cocosenhas.domain.PersonEntity;
import pe.senhas.co.core.cocosenhas.service.PersonService;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.PersonResponse;
import pe.senhas.co.core.cocosenhas.model.PersonRequest;
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
@RequestMapping("v1/person")
@Api(value = "PersonController", produces = "application/json", tags = { "Controlador Person" })
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonEntity> getAllPersons() {
        return personService.findAllPersons();
    }

    @ApiOperation(value = "Obtiene Person por ID", tags = { "Controlador Person" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person encontrada", response = PersonEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable Long id) {
        return personService.findPersonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra Person", tags = { "Controlador Person" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person registrada", response = PersonRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Validated PersonRequest personRequest) {
        personService.savePerson(personRequest);
        return new ResponseEntity<>(new PersonResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Person", tags = { "Controlador Person" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person actualizada", response = PersonRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable Long id, @RequestBody PersonRequest personRequest) throws Exception {
        personService.updatePerson(personRequest, id);
        return new ResponseEntity<>(new PersonResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina Person", tags = { "Controlador Person" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person eliminada", response = PersonRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PersonResponse> deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return new ResponseEntity<>(new PersonResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
