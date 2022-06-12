package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface UfService {

    void create(Uf uf);

    List<Uf> getUfs();

    Optional<Uf> getById(Integer id);

    void update(Uf uf);

    List<Uf> getAll(Uf uf);

}
