package pe.senhas.co.core.cocosenhas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.senhas.co.core.cocosenhas.domain.ParameterEntity;

@Repository
public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {
	
	List<ParameterEntity> findByCodTabla(String codTabla);
}
