package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.Validate;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.CityRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Override
    public void create(City city) {
        validateCity(city);
        repository.save(city);
    }

    @Override
    public List<City> getCities() {
        return repository.findAll();
    }

    @Override
    public Optional<City> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<City> getAll(City filter) {
        return repository.findAll(Example.of(filter));
    }

    @Override
    public void update(City city) {
        validateCity(city);
        repository.save(city);
    }

    private void validateCity(City city) {

        if (repository.existsByNameIgnoreCaseAndUf(city.getName(), city.getUf())){
            throw new BusinessException("Já existe uma cidade com o nome " + city.getName()
                    + " cadastrada no estado " + city.getUf().getName()
                    + ", não é possível ter duas cidades com mesmo nome no mesmo estado.");
        }

        Validate.validateNameRequired(city.getName());

        Validate.validateNameSize(city.getName(), 256);

        Validate.validateStatus(city.getStatus());
    }
}
