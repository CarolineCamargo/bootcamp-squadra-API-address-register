package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create.DistrictDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.City;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.CityService;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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

        City cityEntity = cityService.getById(districtDTO.getCityId())
                .orElseThrow(() -> new BusinessException("Não existe registro com o código Município "
                        + districtDTO.getCityId(), HttpStatus.NOT_FOUND));

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
    public Object getAll(@RequestParam(value = "nome", required = false) String name,
                         @RequestParam(value ="status", required = false) Integer status,
                         @RequestParam(value ="codigoMunicipio", required = false) Integer cityId,
                         @RequestParam(value = "codigoBairro", required = false) Integer id) {

        City cityEntity = null;

        if(cityId != null) {
            cityEntity = cityService.getById(cityId).orElse(City.builder().id(cityId).build());
        }

        District filter = District.builder()
                .name(name)
                .status(status)
                .city(cityEntity)
                .id(id)
                .build();

        List<District> filteredDistrict = service.getAll(filter);

        if (!filteredDistrict.isEmpty() && isFilterByNameOrStatusAndCity(filter)) {
            return filteredDistrict.stream().findFirst().map(district -> modelMapper.map(district, DistrictDTO.class));
        }

        return filteredDistrict.stream()
                .map(district -> modelMapper.map(district, DistrictDTO.class))
                .collect(Collectors.toList());
    }

    private boolean isFilterByNameOrStatusAndCity(District filter) {
        return Objects.nonNull(filter.getId()) || Objects.nonNull(filter.getName()) ||
                (Objects.nonNull(filter.getCity()) && Objects.nonNull(filter.getStatus()));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictDTO> update(@RequestBody DistrictDTO districtDTO) {

        District entity = service.getById(districtDTO.getId())
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Bairro "
                        + districtDTO.getId(), HttpStatus.NOT_FOUND));

        City cityEntity = cityService.getById(districtDTO.getCityId())
                .orElseThrow( () -> new BusinessException("Não existe registro com o código Municipio "
                        + districtDTO.getCityId(), HttpStatus.NOT_FOUND));


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
