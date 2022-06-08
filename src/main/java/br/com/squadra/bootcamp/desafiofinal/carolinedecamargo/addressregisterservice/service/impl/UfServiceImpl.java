package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.UfRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        if(id == null){
            throw new BusinessException("O codigo Uf não pode ser nulo");
        }

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

        if (status != 1 && status != 2){
            throw new BusinessException("O campo status deve ser apenas 1 para ATIVO ou 2 para DESATIVADO");
        }

        return repository.findAllByStatus(status);
    }

    @Override
    public void update(Uf uf) {
        validateUpdateUf(uf);
        repository.save(uf);
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
        if (!StringUtils.hasText(uf.getInitials())){
            throw new BusinessException("O campo sigla é obrigatório.");
        }

        if (!StringUtils.hasText(uf.getName())){
            throw new BusinessException("O campo nome é obrigatório.");
        }

        if(uf.getInitials().length() > 3){
            throw new BusinessException("O campo sigla deve ter até 3 caracteres.");
        }

        if(uf.getName().length() > 60){
            throw new BusinessException("O campo nome deve ter até 60 caracteres.");
        }

        if (uf.getStatus() != 1 && uf.getStatus() != 2){
            throw new BusinessException("O campo status deve ser apenas 1 para ATIVO ou 2 para DESATIVADO");
        }
    }
}
