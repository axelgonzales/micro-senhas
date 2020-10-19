package pe.senhas.co.core.cocosenhas.service;

import pe.senhas.co.core.cocosenhas.domain.PersonEntity;
import pe.senhas.co.core.cocosenhas.model.PersonRequest;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public List<PersonEntity> findAllPersons();

    public Optional<PersonEntity> findPersonById(Long id);

    public PersonEntity savePerson(PersonRequest personRequest);

    public PersonEntity updatePerson(PersonRequest personRequest, Long id);

    public void deletePersonById(Long id);
}
