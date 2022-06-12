package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {

    @JsonProperty("codigoMunicipio")
    private Integer id;

    @JsonProperty("codigoUF")
    private Integer ufId;

    @JsonProperty ("nome")
    private String name;

    private Integer status;
}
