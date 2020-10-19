package pe.senhas.co.core.cocosenhas.controller;

import pe.senhas.co.core.cocosenhas.domain.ProfileEntity;
import pe.senhas.co.core.cocosenhas.service.ProfileService;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.ProfileResponse;
import pe.senhas.co.core.cocosenhas.model.ProfileRequest;
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
@RequestMapping("v1/profile")
@Api(value = "ProfileController", produces = "application/json", tags = { "Controlador Profile" })
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileEntity> getAllProfiles() {
        return profileService.findAllProfiles();
    }

    @ApiOperation(value = "Obtiene Profile por ID", tags = { "Controlador Profile" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile encontrada", response = ProfileEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ProfileEntity> getProfileById(@PathVariable Long id) {
        return profileService.findProfileById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra Profile", tags = { "Controlador Profile" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Profile registrada", response = ProfileRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody @Validated ProfileRequest profileRequest) {
        profileService.saveProfile(profileRequest);
        return new ResponseEntity<>(new ProfileResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Profile", tags = { "Controlador Profile" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile actualizada", response = ProfileRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) throws Exception {
        profileService.updateProfile(profileRequest, id);
        return new ResponseEntity<>(new ProfileResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina Profile", tags = { "Controlador Profile" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile eliminada", response = ProfileRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<ProfileResponse> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfileById(id);
        return new ResponseEntity<>(new ProfileResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
