package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service;


import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {

    Person create(Person person);

    Person update(Person person);

    List<Person> getPeople();

    List<Person> getPeople(Person filter);

    Optional<Person> getById(Integer id);
}
