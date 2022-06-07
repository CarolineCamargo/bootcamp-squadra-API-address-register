package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.DistrictRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<District> getByIdCity(Integer idCity) {
        return repository.findAllByIdCity(idCity);
    }

    @Override
    public Optional<District> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<District> getByStatus(int status) {
        return repository.findAllByStatus(status);
    }

    @Override
    public void update(District district) {
        validateDistrict(district);
        repository.save(district);
    }

    private void validateDistrict(District district) {

        if (repository.existsByNameAndCity(district.getName(), district.getCity())){
            throw new BusinessException("Já existe um bairro com o nome " + district.getName()
                    + " cadastrado na cidade " + district.getCity().getName()
                    + ", não é possível ter dois bairros com mesmo nome na mesma cidade.", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.hasText(district.getName())){
            throw new BusinessException("O campo nome é obrigatório.", HttpStatus.BAD_REQUEST);
        }

        if(district.getName().length() <= 256){
            throw new BusinessException("O campo nome deve ter até 256 caracteres.", HttpStatus.BAD_REQUEST);
        }

        if (!(district.getStatus() == 1 || district.getStatus() == 2)){
            throw new BusinessException("O campo status deve ser apenas 1 para ATIVO ou 2 para DESATIVADO",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
