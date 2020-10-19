package pe.senhas.co.core.cocosenhas.service.impl;

import pe.senhas.co.core.cocosenhas.domain.PersonEntity;
import pe.senhas.co.core.cocosenhas.repository.PersonRepository;
import pe.senhas.co.core.cocosenhas.model.PersonRequest;
import pe.senhas.co.core.cocosenhas.service.impl.mapper.PersonDTOToPersonEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.senhas.co.core.cocosenhas.service.PersonService;
import pe.senhas.co.core.cocosenhas.exception.ModelNotFoundException;
import pe.senhas.co.core.cocosenhas.constant.Constant;


import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private PersonDTOToPersonEntityMapper personDTOToPersonEntityMapper = new PersonDTOToPersonEntityMapper();


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonEntity> findAllPersons() {

        List<PersonEntity> listPerson = personRepository.findAll();

        log.info("GET ALL Person MESSAGE TEST" );
        return listPerson;
    }

    public Optional<PersonEntity> findPersonById(Long id) {

        Optional<PersonEntity> optionalPerson = personRepository.findById(id);

        log.info("GET Person/ MESSAGE TEST" );
        
        return optionalPerson;
    }

    public PersonEntity savePerson(PersonRequest personRequest) {

        PersonEntity personEntity = personRepository.save(personDTOToPersonEntityMapper.personDTOToPersonEntityMapper(personRequest));

        log.info("POST Person MESSAGE TEST" );
        
        return personEntity;
    }

    public PersonEntity updatePerson(PersonRequest personRequest, Long id) {



        return personRepository.findById(id).map(personRequestObje -> {
            personRequest.setId(id);
            PersonEntity person = personRepository.save(personDTOToPersonEntityMapper.personDTOToPersonEntityMapper(personRequest));
            log.info("UPDATE Person MESSAGE TEST" );
            
        return person;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deletePersonById(Long id) {

        personRepository.findById(id).ifPresent(delete -> personRepository.deleteById(id));

        log.info("DELETE Person/ MESSAGE TEST" );
        
    }
}