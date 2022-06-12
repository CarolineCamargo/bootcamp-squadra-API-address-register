package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictDTO{

    @JsonProperty("codigoBairro")
    private Integer id;

    @JsonProperty("codigoMunicipio")
    private Integer cityId;

    @JsonProperty ("nome")
    private String name;

    private Integer status;
}
