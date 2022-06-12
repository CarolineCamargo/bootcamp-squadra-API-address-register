package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CityService {

    void create(City city);

    List<City> getCities();

    Optional<City> getById(Integer id);

    List<City> getAll(City filter);

    void update(City city);
}
