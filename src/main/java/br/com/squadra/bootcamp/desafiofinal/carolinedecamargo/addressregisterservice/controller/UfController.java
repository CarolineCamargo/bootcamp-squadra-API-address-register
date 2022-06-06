package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.UfDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uf")
public class UfController {

    private final UfService service;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UfDTO> create(@RequestBody UfDTO ufDTO){

        Uf entity = Uf.builder()
                .id(ufDTO.getId())
                .initials(ufDTO.getInitials().trim())
                .name(ufDTO.getName().trim())
                .status(ufDTO.getStatus())
                .build();
        service.create(entity);

        return service.getUfs().stream()
                .map(uf -> modelMapper.map(uf, UfDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UfDTO> getUfs(){
        return service.getUfs().stream()
                .map(uf -> modelMapper.map(uf, UfDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping (params = "codigo_UF")
    @ResponseStatus(HttpStatus.OK)
    public UfDTO getById(@RequestParam(value = "codigo_UF") Integer id){
        return service.getById(id)
                .map(uf -> modelMapper.map(uf, UfDTO.class))
                .orElseThrow( () -> new BusinessException("Não existe registro com o código UF " + id,
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "sigla")
    @ResponseStatus(HttpStatus.OK)
    public UfDTO getByInitials(@RequestParam(value = "sigla") String initials){
        return service.getByInitials(initials)
                .map(uf -> modelMapper.map(uf, UfDTO.class))
                .orElseThrow( () -> new BusinessException("Não existe registro com a sigla " + initials,
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public UfDTO getByName(@RequestParam(value = "nome") String name){
        return service.getByName(name)
                .map(uf -> modelMapper.map(uf, UfDTO.class))
                .orElseThrow( () -> new BusinessException("Não existe registro com o nome " + name,
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "status")
    @ResponseStatus(HttpStatus.OK)
    public List<UfDTO> getByStatus(@RequestParam int status){
        return service.getByStatus(status).stream()
                .map(s -> modelMapper.map(s, UfDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UfDTO> update(@RequestBody UfDTO ufDTO){
        Uf entity = service.getById(ufDTO.getId())
                .map(uf -> modelMapper.map(uf, Uf.class))
                .orElseThrow( () -> new BusinessException("Não existe registro com o código UF " + ufDTO.getId(),
                        HttpStatus.NOT_FOUND));

        entity.setId(ufDTO.getId());
        entity.setName(ufDTO.getName());
        entity.setInitials(ufDTO.getInitials());
        entity.setStatus(ufDTO.getStatus());

        service.update(entity);

        return service.getUfs().stream()
                .map(uf -> modelMapper.map(uf, UfDTO.class))
                .collect(Collectors.toList());
    }

}
