package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.UfRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UfServiceImpl implements UfService {

    private final UfRepository repository;

    @Override
    public void create(Uf uf) {
        if (!existsByNameOrInitials(uf)){
            repository.save(uf);
        }
    }

    @Override
    public List<Uf> getUfs() {
        return repository.findAll();
    }

    @Override
    public Optional<Uf> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Uf> getByInitials(String initials) {
        return repository.findByInitials(initials);
    }

    @Override
    public Optional<Uf> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Uf> getByStatus(int status) {
        return repository.findAllByStatus(status);
    }

    @Override
    public Uf update(Uf uf) {
        return repository.save(uf);
    }

    private boolean existsByNameOrInitials(Uf uf){

        if (repository.existsByInitialsIgnoreCase(uf.getInitials())){
            throw new BusinessException("Já existe um estado com a sigla " + uf.getInitials()
                    + ", você não pode cadastrar dois estados com a mesma sigla", HttpStatus.BAD_REQUEST);
        }
        if (repository.existsByNameIgnoreCase(uf.getName())){
            throw new BusinessException("Já existe um estado com o nome " + uf.getName()
                    + ", você não pode cadastrar dois estados com o mesmo nome", HttpStatus.BAD_REQUEST);
        }
        return false;
    }
}
