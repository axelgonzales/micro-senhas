package pe.senhas.co.core.cocosenhas.service;

import pe.senhas.co.core.cocosenhas.domain.UserEntity;
import pe.senhas.co.core.cocosenhas.model.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> findAllUsers();

    public Optional<UserEntity> findUserById(Long id);

    public UserEntity saveUser(UserRequest userRequest);

    public UserEntity updateUser(UserRequest userRequest, Long id);

    public void deleteUserById(Long id);
}
