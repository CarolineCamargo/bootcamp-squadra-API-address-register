package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create.UfDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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
    public Object getAll(@RequestParam(value = "nome", required = false) String name,
                              @RequestParam(value ="status", required = false) Integer status,
                              @RequestParam(value ="sigla", required = false) String initials,
                              @RequestParam(value = "codigoUF", required = false) Integer id) {

        Uf filter = Uf.builder()
                .name(name)
                .status(status)
                .initials(initials)
                .id(id)
                .build();

        List<Uf> filteredUfs = service.getAll(filter);

        if (!filteredUfs.isEmpty() && isUfFilterByIdOrNameOrInitials(filter)) {
            return filteredUfs.stream().findFirst().map(uf -> modelMapper.map(uf, UfDTO.class));
        }

        return filteredUfs.stream().map(uf -> modelMapper.map(uf, UfDTO.class)).collect(Collectors.toList());
    }

    private boolean isUfFilterByIdOrNameOrInitials(Uf filter) {
        return Objects.nonNull(filter.getId())
                || Objects.nonNull(filter.getInitials())
                || Objects.nonNull(filter.getName());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UfDTO> update(@RequestBody UfDTO ufDTO){
        Uf entity = service.getById(ufDTO.getId())
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
