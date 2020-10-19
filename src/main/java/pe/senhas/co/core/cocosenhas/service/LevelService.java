package pe.senhas.co.core.cocosenhas.service;

import pe.senhas.co.core.cocosenhas.domain.LevelEntity;
import pe.senhas.co.core.cocosenhas.model.LevelRequest;
import pe.senhas.co.core.cocosenhas.model.LevelSearch;

import java.util.List;
import java.util.Optional;

public interface LevelService {

    public List<LevelEntity> findAllLevels();

    public Optional<LevelEntity> findLevelById(Long id);

    public LevelEntity saveLevel(LevelRequest levelRequest);

    public LevelEntity updateLevel(LevelRequest levelRequest);

    public void deleteLevelById(Long id);

	public List<LevelEntity> findLevelSearch(LevelSearch search);
}
