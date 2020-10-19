package pe.senhas.co.core.cocosenhas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.senhas.co.core.cocosenhas.domain.LevelEntity;
import pe.senhas.co.core.cocosenhas.model.LevelRequest;
import pe.senhas.co.core.cocosenhas.model.LevelSearch;
import pe.senhas.co.core.cocosenhas.repository.LevelRepository;
import pe.senhas.co.core.cocosenhas.service.LevelService;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.LevelDTOToLevelEntityMapper;

@Slf4j
@Service
@Transactional
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private LevelDTOToLevelEntityMapper levelDTOToLevelEntityMapper = new LevelDTOToLevelEntityMapper();


    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public List<LevelEntity> findAllLevels() {

        List<LevelEntity> listLevel = levelRepository.findAll();

        log.info("GET ALL Level MESSAGE TEST" );
        return listLevel;
    }

    public Optional<LevelEntity> findLevelById(Long id) {

        Optional<LevelEntity> optionalLevel = levelRepository.findById(id);

        log.info("GET Level/ MESSAGE TEST" );
        
        return optionalLevel;
    }

    public LevelEntity saveLevel(LevelRequest levelRequest) {
    	System.out.println(levelDTOToLevelEntityMapper.levelDTOToLevelEntityMapper(levelRequest).toString());
    	LevelEntity levelEntity = null;
    	try {
    		levelEntity = levelRepository.save(levelDTOToLevelEntityMapper.levelDTOToLevelEntityMapper(levelRequest));
		} catch (Exception e) {
			System.out.println("errpr" + e.getLocalizedMessage());
		}
        

        log.info("POST Level MESSAGE TEST" );
        
        return levelEntity;
    }



    public void deleteLevelById(Long id) {

        levelRepository.findById(id).ifPresent(delete -> levelRepository.deleteById(id));

        log.info("DELETE Level/ MESSAGE TEST" );
        
    }

	@Override
	public List<LevelEntity> findLevelSearch(LevelSearch search) {
		return levelRepository.findByCountryId(search.getCountryId());
	}

	@Override
	public LevelEntity updateLevel(LevelRequest levelRequest) {
		LevelEntity levelEntity = levelRepository.findByCountryIdAndCategoryId(levelRequest.getCountryId(), levelRequest.getCategoryId());
		levelEntity.setStateId(levelRequest.getStateId());
		levelRepository.save(levelEntity);
		return levelEntity;
	}
}