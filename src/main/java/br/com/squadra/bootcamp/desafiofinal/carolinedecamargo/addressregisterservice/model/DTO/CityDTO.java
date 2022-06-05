package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {

    @JsonProperty("codigoMunicipio")
    private Integer id;

    @JsonProperty("codigoUf")
    private Integer idUf;

    @JsonProperty ("nome")
    private String name;

    private int status;
}
