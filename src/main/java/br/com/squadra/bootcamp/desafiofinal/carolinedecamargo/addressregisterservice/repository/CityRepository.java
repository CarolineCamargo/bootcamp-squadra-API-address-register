package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    boolean existsByNameIgnoreCaseAndUf(String name, Uf uf);
}