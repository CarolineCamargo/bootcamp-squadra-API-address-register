package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UfRepository extends JpaRepository<Uf, Integer> {

    boolean existsByInitialsIgnoreCase(String initials);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByInitialsIgnoreCaseAndIdNot(String initials, Integer id);

    boolean existsByNameIgnoreCaseAndIdNot(String name);

    Optional<Uf> findByInitials(String initials);

    Optional<Uf> findByName(String name);

    List<Uf> findAllByStatus(int status);
}