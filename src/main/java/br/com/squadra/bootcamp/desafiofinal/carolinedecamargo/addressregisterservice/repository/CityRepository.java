package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    boolean existsByNameAndUf(String name);

    List<City> findAllByIdUf(Integer idUf);

    Optional<City> findByName(String name);

    List<City> findAllByStatus(int status);
}