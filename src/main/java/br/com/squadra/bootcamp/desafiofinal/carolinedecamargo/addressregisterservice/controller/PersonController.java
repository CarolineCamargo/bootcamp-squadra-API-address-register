package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create.AddressDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create.PersonDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Address;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Person;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.AddressService;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.DistrictService;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/pessoa")
public class PersonController {

    private final PersonService service;
    private final AddressService addressService;
    private final DistrictService districtService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> create(@RequestBody PersonDTO personDTO){

        Person entity = Person.builder()
                .name(personDTO.getName())
                .lastName(personDTO.getLastName())
                .age(personDTO.getAge())
                .login(personDTO.getLogin())
                .password(personDTO.getPassword())
                .status(personDTO.getStatus())
                .build();

        Person person = service.create(entity);

        List<Address> addresses = personDTO.getAddressDTOs().stream()
                .map(a -> createAddress(a, person))
                .peek(Validate::validateAddress)
                .collect(Collectors.toList());

        person.setAddress(addresses);
        service.update(entity);

        return service.getPeople().stream()
                .map(Person::toDTO)
                .collect(Collectors.toList());
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object getAll(@RequestParam(value = "codigoPessoa", required = false) Integer id,
                         @RequestParam(value = "nome", required = false) String name,
                         @RequestParam(value = "sobrenome", required = false) String lastName,
                         @RequestParam(value = "idade", required = false) Integer age,
                         @RequestParam(value = "login", required = false) String login,
                         @RequestParam(value = "senha", required = false) String password,
                         @RequestParam(value ="status", required = false) Integer status){

        Person filter = Person.builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .age(age)
                .login(login)
                .password(password)
                .status(status)
                .build();

        List<Person> filteredPerson = service.getPeople(filter);

        if (!filteredPerson.isEmpty() && isPersonFilterByOnlyId(filter)) {
            return filteredPerson.stream().findFirst().map(Person::toDTOWithAddress);
        }

        return filteredPerson.stream()
                .map(Person::toDTO);
    }

    private boolean isPersonFilterByOnlyId(Person filter) {
        return filter.getId() != null && (Objects.isNull(filter.getName())
                && Objects.isNull(filter.getLastName())
                && Objects.isNull(filter.getAge())
                && Objects.isNull(filter.getLogin())
                && Objects.isNull(filter.getPassword())
                && Objects.isNull(filter.getStatus()));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> update(@RequestBody PersonDTO personDTO){

        Person entity = service.getById(personDTO.getId())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código Pessoa "
                        + personDTO.getId()));

        entity.setName(personDTO.getName());
        entity.setLastName(personDTO.getLastName());
        entity.setAge(personDTO.getAge());
        entity.setLogin(personDTO.getLogin());
        entity.setPassword(personDTO.getPassword());
        entity.setStatus(personDTO.getStatus());

        List<Address> addresses = personDTO.getAddressDTOs()
                .stream()
                    .map(dto -> createOrUpdateAddress(entity, dto))
                    .peek(Validate::validateAddress)
                    .collect(Collectors.toList());

        List<Address> addressToRemove = entity.getAddress().stream()
                .filter(a -> !addresses.contains(a))
                .collect(Collectors.toList());

        entity.getAddress().removeAll(addressToRemove);

        addressService.delete(addressToRemove);

        entity.getAddress().addAll(addresses);
        service.update(entity);

        return service.getPeople().stream()
                .map(Person::toDTO)
                .collect(Collectors.toList());
    }

    private Address createOrUpdateAddress(Person entity, AddressDTO dto) {

        if (dto.getId() == null || dto.getId() == 0) {
            return createAddress(dto, entity);
        }

        return updateAddress(dto, entity.getAddress());
    }

    private Address updateAddress(AddressDTO addressDTO, List<Address> addresses) {
        District district = districtService.getById(addressDTO.getDistrictId())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código Bairro "
                        + addressDTO.getDistrictId()));

        return addresses.stream()
                .filter(a -> a.getId().equals(addressDTO.getId()))
                .peek(a -> {
                    a.setDistrict(district);
                    a.setCep(addressDTO.getCep());
                    a.setComplement(addressDTO.getComplement());
                    a.setNumber(addressDTO.getNumber());
                    a.setStreet(addressDTO.getStreet());
                }).findFirst()
                .orElseThrow(() -> new BusinessException("Ocorreu um erro ao tentar atualizar os endereços"));
    }


    private Address createAddress(AddressDTO addressDTO, Person person) {
        District district = districtService.getById(addressDTO.getDistrictId())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código Bairro "
                        + addressDTO.getDistrictId()));

        return Address.builder()
                .person(person)
                .district(district)
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .cep(addressDTO.getCep())
                .build();
    }
}
