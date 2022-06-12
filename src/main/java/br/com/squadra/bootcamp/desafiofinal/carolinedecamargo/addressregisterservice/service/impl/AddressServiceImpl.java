package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.impl;


import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Address;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.repository.AddressRepository;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.AddressService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Override
    public void create(List<Address> address) {
        repository.saveAll(address);
    }

    @Override
    public List<Address> findAll(List<Integer> codigos) {
        return repository.findAllById(codigos);
    }

    @Override
    public void delete(List<Address> address) {
        address.forEach(repository::delete);
    }


}
