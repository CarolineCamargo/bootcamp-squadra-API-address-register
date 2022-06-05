package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.UfRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UfServiceImpl implements UfService {

    private final UfRepository repository;

    @Override
    public Uf create(Uf uf) {
        if (repository.existsByInitialsIgnoreCase(uf.getInitials())){
            throw new BusinessException("Já existe um estado com a sigla " + uf.getInitials()
                    + ", você não pode cadastrar dois estados com a mesma sigla", HttpStatus.BAD_REQUEST);
        }
        if (repository.existsByNameIgnoreCase(uf.getName())){
            throw new BusinessException("Já existe um estado com o nome " + uf.getName()
                    + ", você não pode cadastrar dois estados com o mesmo nome", HttpStatus.BAD_REQUEST);
        }
        return repository.save(uf);
    }

    @Override
    public List<Uf> getUfs() {
        return repository.findAll();
    }
}
