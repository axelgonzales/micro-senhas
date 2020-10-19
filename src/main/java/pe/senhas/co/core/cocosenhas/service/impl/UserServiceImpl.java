package pe.senhas.co.core.cocosenhas.service.impl;

import pe.senhas.co.core.cocosenhas.domain.UserEntity;
import pe.senhas.co.core.cocosenhas.repository.UserRepository;
import pe.senhas.co.core.cocosenhas.model.UserRequest;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.UserDTOToUserEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.senhas.co.core.cocosenhas.service.UserService;
import pe.senhas.co.core.cocosenhas.exception.ModelNotFoundException;
import pe.senhas.co.core.cocosenhas.constant.Constant;


import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserDTOToUserEntityMapper userDTOToUserEntityMapper = new UserDTOToUserEntityMapper();


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAllUsers() {

        List<UserEntity> listUser = userRepository.findAll();

        log.info("GET ALL User MESSAGE TEST" );
        return listUser;
    }

    public Optional<UserEntity> findUserById(Long id) {

        Optional<UserEntity> optionalUser = userRepository.findById(id);

        log.info("GET User/ MESSAGE TEST" );
        
        return optionalUser;
    }

    public UserEntity saveUser(UserRequest userRequest) {

        UserEntity userEntity = userRepository.save(userDTOToUserEntityMapper.userDTOToUserEntityMapper(userRequest));

        log.info("POST User MESSAGE TEST" );
        
        return userEntity;
    }

    public UserEntity updateUser(UserRequest userRequest, Long id) {



        return userRepository.findById(id).map(userRequestObje -> {
            userRequest.setId(id);
            UserEntity user = userRepository.save(userDTOToUserEntityMapper.userDTOToUserEntityMapper(userRequest));
            log.info("UPDATE User MESSAGE TEST" );
            
        return user;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteUserById(Long id) {

        userRepository.findById(id).ifPresent(delete -> userRepository.deleteById(id));

        log.info("DELETE User/ MESSAGE TEST" );
        
    }
}