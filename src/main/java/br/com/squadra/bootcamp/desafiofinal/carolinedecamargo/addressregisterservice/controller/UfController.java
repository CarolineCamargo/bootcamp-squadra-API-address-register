package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.UfDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
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
    public List<UfDTO> create (@RequestBody UfDTO ufDTO){

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
}
