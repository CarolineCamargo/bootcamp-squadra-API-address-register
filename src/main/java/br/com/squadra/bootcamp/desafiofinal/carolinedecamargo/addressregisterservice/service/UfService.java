package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UfService {

    Uf create(Uf uf);

    List<Uf> getUfs();
}
