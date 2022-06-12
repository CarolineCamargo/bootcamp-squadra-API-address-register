package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    boolean existsByNameIgnoreCaseAndCity(String name, City city);
}