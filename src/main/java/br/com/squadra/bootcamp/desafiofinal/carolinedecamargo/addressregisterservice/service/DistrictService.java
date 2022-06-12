package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {

    void create(District district);

    List<District> getDistricts();

    Optional<District> getById(Integer id);

    void update(District district);

    List<District> getAll(District filter);
}
