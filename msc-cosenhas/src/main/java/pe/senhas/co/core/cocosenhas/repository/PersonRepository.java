package pe.senhas.co.core.cocosenhas.repository;

import pe.senhas.co.core.cocosenhas.domain.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
