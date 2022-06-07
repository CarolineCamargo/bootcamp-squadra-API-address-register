package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.DistrictDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.CityService;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bairro")
public class DistrictController {

    private final DistrictService service;
    private final CityService cityService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictDTO> create(@RequestBody DistrictDTO districtDTO){

        City cityEntity = cityService.getById(districtDTO.getIdCity())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código Município "
                        + districtDTO.getIdCity(), HttpStatus.NOT_FOUND));

        District entity = District.builder()
                .name(districtDTO.getName().trim())
                .city(cityEntity)
                .status(districtDTO.getStatus())
                .build();

        service.create(entity);

        return service.getDistricts().stream()
                .map(district -> modelMapper.map(district, DistrictDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictDTO> get(){
        return service.getDistricts().stream()
                .map(district -> modelMapper.map(district, DistrictDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping (params = "codigoBairro")
    @ResponseStatus(HttpStatus.OK)
    public DistrictDTO getById(@RequestParam("codigoBairro") Integer id){
        return service.getById(id)
                .map(district -> modelMapper.map(district, DistrictDTO.class))
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Bairro " + id,
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "codigoMunicipio")
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictDTO> getByIdCity(@RequestParam("codigoMunicipio") Integer idCity){
        return service.getByIdCity(idCity).stream()
                .map(district -> modelMapper.map(district, DistrictDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public DistrictDTO getByName(@RequestParam("nome") String name){
        return service.getByName(name)
                .map(district -> modelMapper.map(district, DistrictDTO.class))
                .orElseThrow( () -> new BusinessException("Não existe registro com o nome " + name,
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "status")
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictDTO> getByStatus(@RequestParam int status){
        return service.getByStatus(status).stream()
                .map(s -> modelMapper.map(s, DistrictDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictDTO> update(@RequestBody DistrictDTO districtDTO) {

        District entity = service.getById(districtDTO.getId())
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Bairro "
                        + districtDTO.getId(), HttpStatus.NOT_FOUND));

        City cityEntity = cityService.getById(districtDTO.getIdCity())
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Municipio "
                        + districtDTO.getIdCity(), HttpStatus.NOT_FOUND));


        entity.setId(districtDTO.getId());
        entity.setCity(cityEntity);
        entity.setName(districtDTO.getName().trim());
        entity.setStatus(districtDTO.getStatus());

        service.update(entity);

        return service.getDistricts().stream()
                .map(district -> modelMapper.map(district, DistrictDTO.class))
                .collect(Collectors.toList());
    }
}
