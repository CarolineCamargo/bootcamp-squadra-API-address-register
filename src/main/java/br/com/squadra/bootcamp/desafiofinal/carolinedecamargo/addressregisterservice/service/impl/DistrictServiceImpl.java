package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.Validate;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.DistrictRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository repository;

    @Override
    public void create(District district) {
        validateDistrict(district);
        repository.save(district);
    }

    @Override
    public List<District> getDistricts() {
        return repository.findAll();
    }

    @Override
    public Optional<District> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<District> getAll(District filter) {
        return repository.findAll(Example.of(filter));
    }

    @Override
    public void update(District district) {
        validateDistrict(district);
        repository.save(district);
    }

    private void validateDistrict(District district) {

        if (repository.existsByNameIgnoreCaseAndCity(district.getName(), district.getCity())){
            throw new BusinessException("Já existe um bairro com o nome " + district.getName()
                    + " cadastrado na cidade " + district.getCity().getName()
                    + ", não é possível ter dois bairros com mesmo nome na mesma cidade.");
        }

        Validate.validateNameRequired(district.getName());

        Validate.validateNameSize(district.getName(), 256);

        Validate.validateStatus(district.getStatus());
    }
}
