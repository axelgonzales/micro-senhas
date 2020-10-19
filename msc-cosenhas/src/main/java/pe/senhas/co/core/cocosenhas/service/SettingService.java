package pe.senhas.co.core.cocosenhas.service;

import pe.senhas.co.core.cocosenhas.domain.SettingEntity;
import pe.senhas.co.core.cocosenhas.model.SettingRequest;

import java.util.List;
import java.util.Optional;

public interface SettingService {

    public List<SettingEntity> findAllSettings();

    public Optional<SettingEntity> findSettingById(Long id);

    public SettingEntity saveSetting(SettingRequest settingRequest);

    public SettingEntity updateSetting(SettingRequest settingRequest, Long id);

    public void deleteSettingById(Long id);
}
