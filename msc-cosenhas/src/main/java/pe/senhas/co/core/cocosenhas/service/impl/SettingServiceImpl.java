package pe.senhas.co.core.cocosenhas.service.impl;

import pe.senhas.co.core.cocosenhas.domain.SettingEntity;
import pe.senhas.co.core.cocosenhas.repository.SettingRepository;
import pe.senhas.co.core.cocosenhas.model.SettingRequest;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.SettingDTOToSettingEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.senhas.co.core.cocosenhas.service.SettingService;
import pe.senhas.co.core.cocosenhas.exception.ModelNotFoundException;
import pe.senhas.co.core.cocosenhas.constant.Constant;


import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class SettingServiceImpl implements SettingService {

    private final SettingRepository settingRepository;
    private SettingDTOToSettingEntityMapper settingDTOToSettingEntityMapper = new SettingDTOToSettingEntityMapper();


    @Autowired
    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public List<SettingEntity> findAllSettings() {

        List<SettingEntity> listSetting = settingRepository.findAll();

        log.info("GET ALL Setting MESSAGE TEST" );
        return listSetting;
    }

    public Optional<SettingEntity> findSettingById(Long id) {

        Optional<SettingEntity> optionalSetting = settingRepository.findById(id);

        log.info("GET Setting/ MESSAGE TEST" );
        
        return optionalSetting;
    }

    public SettingEntity saveSetting(SettingRequest settingRequest) {

        SettingEntity settingEntity = settingRepository.save(settingDTOToSettingEntityMapper.settingDTOToSettingEntityMapper(settingRequest));

        log.info("POST Setting MESSAGE TEST" );
        
        return settingEntity;
    }

    public SettingEntity updateSetting(SettingRequest settingRequest, Long id) {



        return settingRepository.findById(id).map(settingRequestObje -> {
            settingRequest.setId(id);
            SettingEntity setting = settingRepository.save(settingDTOToSettingEntityMapper.settingDTOToSettingEntityMapper(settingRequest));
            log.info("UPDATE Setting MESSAGE TEST" );
            
        return setting;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteSettingById(Long id) {

        settingRepository.findById(id).ifPresent(delete -> settingRepository.deleteById(id));

        log.info("DELETE Setting/ MESSAGE TEST" );
        
    }
}