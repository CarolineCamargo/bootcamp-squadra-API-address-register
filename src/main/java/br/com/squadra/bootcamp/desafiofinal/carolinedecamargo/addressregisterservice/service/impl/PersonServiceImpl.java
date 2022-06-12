package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.Validate;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Person;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.PersonRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public Person create(Person person) {
        validatePerson(person);
        return save(person);
    }

    @Override
    public Person update(Person person) {
        validatePerson(person);
        return save(person);
    }

    private Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public List<Person> getPeople() {
        return repository.findAll();
    }

    public List<Person> getPeople(Person filter) {
        return repository.findAll(Example.of(filter));
    }

    @Override
    public Optional<Person> getById(Integer id) {
        return repository.findById(id);
    }

    private void validatePerson(Person person){

        if ((person.getId() == null && repository.existsByLogin(person.getLogin())) ||
            (repository.existsByLoginAndIdNot(person.getLogin(), person.getId()))) {

            throw new BusinessException("O login " + person.getLogin()
                    + " já existe, não é possível cadastrar novamente.");
        }

        Validate.validateNameRequired(person.getName());

        Validate.validateLastNameRequired(person.getLastName());

        Validate.validateAgeRequired(person.getAge());

        Validate.validateLoginRequired(person.getLogin());

        Validate.validatePasswordRequired(person.getPassword());

        Validate.validateNameSize(person.getName(), 256);

        Validate.validateLastNameSize(person.getLastName(), 256);

        Validate.validateAgeSize(person.getAge());

        Validate.validateLoginSize(person.getLogin(), 50);

        Validate.validatePasswordSize(person.getPassword(), 50);

        Validate.validateStatus(person.getStatus());
    }

}
