package pe.senhas.co.core.cocosenhas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.senhas.co.core.cocosenhas.domain.LevelEntity;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {
	
	List<LevelEntity>  findByCountryId(Long countryId);
	LevelEntity findByCountryIdAndCategoryId(Long countryId,Long categoryId);
	
}
