package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
}