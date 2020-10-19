package pe.senhas.co.core.cocosenhas.repository;

import pe.senhas.co.core.cocosenhas.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
