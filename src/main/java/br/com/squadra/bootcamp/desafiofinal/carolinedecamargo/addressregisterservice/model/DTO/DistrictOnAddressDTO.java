package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictOnAddressDTO{

    @JsonProperty("codigoBairro")
    private Integer id;

    @JsonProperty("codigoMunicipio")
    private Integer idCity;

    @JsonProperty ("nome")
    private String name;

    private int status;

    @JsonProperty("municipio")
    private CityOnDistrictDTO cityOnDistrictDTO;
}