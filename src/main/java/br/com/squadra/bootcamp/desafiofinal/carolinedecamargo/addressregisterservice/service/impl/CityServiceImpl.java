package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.CityRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        if(id == null){
            throw new BusinessException("O codigo Municipio não pode ser nulo");
        }

        return repository.findById(id);
    }

    @Override
    public List<City> getByUf(Uf uf) {
        return repository.findAllByUf(uf);
    }

    @Override
    public List<City> getByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public List<City> getByStatus(int status) {

        if (status != 1 && status != 2){
            throw new BusinessException("O campo status deve ser apenas 1 para ATIVO ou 2 para DESATIVADO");
        }

        return repository.findAllByStatus(status);
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

        if (!StringUtils.hasText(city.getName())){
            throw new BusinessException("O campo nome é obrigatório.");
        }

        if(city.getName().length() > 256){
            throw new BusinessException("O campo nome deve ter até 256 caracteres.");
        }

        if (city.getStatus() != 1 && city.getStatus() != 2){
            throw new BusinessException("O campo status deve ser apenas 1 para ATIVO ou 2 para DESATIVADO");
        }
    }
}
