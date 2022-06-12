package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create.CityDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.CityService;
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
@RequestMapping("/municipio")
public class CityController {

    private final CityService service;
    private final UfService ufService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityDTO> create(@RequestBody CityDTO cityDTO){

        System.out.println(cityDTO.getUfId());
        Uf ufEntity = ufService.getById(cityDTO.getUfId())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código UF "
                        + cityDTO.getUfId(), HttpStatus.NOT_FOUND));

        City entity = City.builder()
                .name(cityDTO.getName().trim())
                .uf(ufEntity)
                .status(cityDTO.getStatus())
                .build();

        service.create(entity);

        return service.getCities().stream()
                .map(city -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object getAll(@RequestParam(value = "nome", required = false) String name,
                         @RequestParam(value ="status", required = false) Integer status,
                         @RequestParam(value ="codigoMunicipio", required = false) Integer id,
                         @RequestParam(value = "codigoUF", required = false) Integer ufId) {

        Uf ufEntity = null;

        if(ufId != null) {
            ufEntity = ufService.getById(ufId).orElse(Uf.builder().id(ufId).build());
        }

        City filter = City.builder()
                .name(name)
                .status(status)
                .uf(ufEntity)
                .id(id)
                .build();

        List<City> filteredCity = service.getAll(filter);

        if (!filteredCity.isEmpty() && (isCityFilterByIdOrName(filter))) {
            return filteredCity.stream().findFirst().map(city -> modelMapper.map(city, CityDTO.class));
        }

        return filteredCity.stream().map(city -> modelMapper.map(city, CityDTO.class)).collect(Collectors.toList());
    }


    private boolean isCityFilterByIdOrName(City filter) {
        return (Objects.nonNull(filter.getId())
                    || Objects.nonNull(filter.getName()))
                    || (Objects.nonNull(filter.getStatus())
                    && Objects.nonNull(filter.getUf()));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityDTO> update(@RequestBody CityDTO cityDTO) {
        
        City entity = service.getById(cityDTO.getId())
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Municipio "
                        + cityDTO.getId(), HttpStatus.NOT_FOUND));

        Uf ufEntity = ufService.getById(cityDTO.getUfId())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código UF " + cityDTO.getUfId(),
                        HttpStatus.NOT_FOUND));

        entity.setId(cityDTO.getId());
        entity.setUf(ufEntity);
        entity.setName(cityDTO.getName().trim());
        entity.setStatus(cityDTO.getStatus());

        service.update(entity);

        return service.getCities().stream()
                .map(city -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }

}
