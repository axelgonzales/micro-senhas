package pe.senhas.co.core.cocosenhas.service;

import pe.senhas.co.core.cocosenhas.domain.ProfileEntity;
import pe.senhas.co.core.cocosenhas.model.ProfileRequest;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    public List<ProfileEntity> findAllProfiles();

    public Optional<ProfileEntity> findProfileById(Long id);

    public ProfileEntity saveProfile(ProfileRequest profileRequest);

    public ProfileEntity updateProfile(ProfileRequest profileRequest, Long id);

    public void deleteProfileById(Long id);
}
