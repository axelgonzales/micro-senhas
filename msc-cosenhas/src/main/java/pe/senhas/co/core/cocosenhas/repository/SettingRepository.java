package pe.senhas.co.core.cocosenhas.repository;

import pe.senhas.co.core.cocosenhas.domain.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, Long> {
}
