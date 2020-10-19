package pe.senhas.co.core.cocosenhas.service.impl;

import pe.senhas.co.core.cocosenhas.domain.ProfileEntity;
import pe.senhas.co.core.cocosenhas.repository.ProfileRepository;
import pe.senhas.co.core.cocosenhas.model.ProfileRequest;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.ProfileDTOToProfileEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.senhas.co.core.cocosenhas.service.ProfileService;
import pe.senhas.co.core.cocosenhas.exception.ModelNotFoundException;
import pe.senhas.co.core.cocosenhas.constant.Constant;


import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private ProfileDTOToProfileEntityMapper profileDTOToProfileEntityMapper = new ProfileDTOToProfileEntityMapper();


    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileEntity> findAllProfiles() {

        List<ProfileEntity> listProfile = profileRepository.findAll();

        log.info("GET ALL Profile MESSAGE TEST" );
        return listProfile;
    }

    public Optional<ProfileEntity> findProfileById(Long id) {

        Optional<ProfileEntity> optionalProfile = profileRepository.findById(id);

        log.info("GET Profile/ MESSAGE TEST" );
        
        return optionalProfile;
    }

    public ProfileEntity saveProfile(ProfileRequest profileRequest) {

        ProfileEntity profileEntity = profileRepository.save(profileDTOToProfileEntityMapper.profileDTOToProfileEntityMapper(profileRequest));

        log.info("POST Profile MESSAGE TEST" );
        
        return profileEntity;
    }

    public ProfileEntity updateProfile(ProfileRequest profileRequest, Long id) {



        return profileRepository.findById(id).map(profileRequestObje -> {
            profileRequest.setId(id);
            ProfileEntity profile = profileRepository.save(profileDTOToProfileEntityMapper.profileDTOToProfileEntityMapper(profileRequest));
            log.info("UPDATE Profile MESSAGE TEST" );
            
        return profile;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteProfileById(Long id) {

        profileRepository.findById(id).ifPresent(delete -> profileRepository.deleteById(id));

        log.info("DELETE Profile/ MESSAGE TEST" );
        
    }
}