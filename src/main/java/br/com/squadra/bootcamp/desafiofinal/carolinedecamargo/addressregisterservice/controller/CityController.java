package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.CityDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.UfDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Uf;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.CityService;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.UfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        System.out.println(cityDTO.getIdUf());
        Uf ufEntity = ufService.getById(cityDTO.getIdUf())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código UF "
                        + cityDTO.getIdUf(), HttpStatus.NOT_FOUND));

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
    public List<CityDTO> get(){
        return service.getCities().stream()
                .map(city -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping (params = "codigoMunicipio")
    @ResponseStatus(HttpStatus.OK)
    public CityDTO getById(@RequestParam("codigoMunicipio") Integer id){
        return service.getById(id)
                .map(city -> modelMapper.map(city, CityDTO.class))
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Municipio " + id,
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "codigoUF")
    @ResponseStatus(HttpStatus.OK)
    public List<CityDTO> getByIdUf(@RequestParam("codigoUF") Integer idUf){

        Uf ufEntity = ufService.getById(idUf)
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Uf " + idUf,
                        HttpStatus.NOT_FOUND));

        return service.getByUf(ufEntity).stream()
                .map(city -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public List<CityDTO> getByName(@RequestParam("nome") String name){
        return service.getByName(name).stream()
                .map(city -> modelMapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(params = "status")
    @ResponseStatus(HttpStatus.OK)
    public List<CityDTO> getByStatus(@RequestParam int status){
        return service.getByStatus(status).stream()
                .map(s -> modelMapper.map(s, CityDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityDTO> update(@RequestBody CityDTO cityDTO) {
        
        City entity = service.getById(cityDTO.getId())
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Municipio "
                        + cityDTO.getId(), HttpStatus.NOT_FOUND));

        Uf ufEntity = ufService.getById(cityDTO.getIdUf())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código UF " + cityDTO.getIdUf(),
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
