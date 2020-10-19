package pe.senhas.co.core.cocosenhas.repository;

import pe.senhas.co.core.cocosenhas.domain.VideoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
	
	List<VideoEntity> findByStateId(Long stateId);
}
