package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.Validate;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.UfRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UfServiceImpl implements UfService {

    private final UfRepository repository;

    @Override
    public void create(Uf uf) {
        validateCreateUf(uf);
        repository.save(uf);
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
    public void update(Uf uf) {
        validateUpdateUf(uf);
        repository.save(uf);
    }

    @Override
    public List<Uf> getAll(Uf filter) {
        return repository.findAll(Example.of(filter));
    }

    private void validateCreateUf(Uf uf){

        if (repository.existsByInitialsIgnoreCase(uf.getInitials())){
            throw new BusinessException("Já existe um estado com a sigla " + uf.getInitials()
                    + ", você não pode cadastrar dois estados com a mesma sigla");
        }
        if (repository.existsByNameIgnoreCase(uf.getName())){
            throw new BusinessException("Já existe um estado com o nome " + uf.getName()
                    + ", você não pode cadastrar dois estados com o mesmo nome");
        }

        validateUf(uf);
    }

    private void validateUpdateUf(Uf uf){

        if (repository.existsByInitialsIgnoreCaseAndIdNot(uf.getInitials(), uf.getId())){
            throw new BusinessException("Já existe um outro estado com a sigla " + uf.getInitials()
                    + ", você não pode atualizar o registro com a mesma sigla");
        }
        if (repository.existsByNameIgnoreCaseAndIdNot(uf.getName(), uf.getId())){
            throw new BusinessException("Já existe um outro estado com o nome " + uf.getName()
                    + ", você não pode atualizar o registro com o mesmo nome");
        }

        validateUf(uf);
    }

    private void validateUf(Uf uf) {

        Validate.validateInitialsRequired(uf.getInitials());

        Validate.validateNameRequired(uf.getName());

        Validate.validateInitialsSize(uf.getInitials());

        Validate.validateNameSize(uf.getName(), 60);

        Validate.validateStatus(uf.getStatus());
    }
}
