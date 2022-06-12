package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    void create(List<Address> address);

    List<Address> findAll(List<Integer> codigos);

    void delete(List<Address> addressToRemove);
}
