package pe.senhas.co.core.cocosenhas.repository;

import pe.senhas.co.core.cocosenhas.domain.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
