package pe.senhas.co.core.cocosenhas.controller;

import pe.senhas.co.core.cocosenhas.domain.UserEntity;
import pe.senhas.co.core.cocosenhas.service.UserService;
import pe.senhas.co.core.cocosenhas.exception.ExceptionResponse;
import pe.senhas.co.core.cocosenhas.model.UserResponse;
import pe.senhas.co.core.cocosenhas.model.UserRequest;
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
@RequestMapping("v1/user")
@Api(value = "UserController", produces = "application/json", tags = { "Controlador User" })
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAllUsers();
    }

    @ApiOperation(value = "Obtiene User por ID", tags = { "Controlador User" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User encontrada", response = UserEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra User", tags = { "Controlador User" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User registrada", response = UserRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserResponse> createUser(@RequestBody @Validated UserRequest userRequest) {
        userService.saveUser(userRequest);
        return new ResponseEntity<>(new UserResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza User", tags = { "Controlador User" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User actualizada", response = UserRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) throws Exception {
        userService.updateUser(userRequest, id);
        return new ResponseEntity<>(new UserResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina User", tags = { "Controlador User" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User eliminada", response = UserRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(new UserResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
