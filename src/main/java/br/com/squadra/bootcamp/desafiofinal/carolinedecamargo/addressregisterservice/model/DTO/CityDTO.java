package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.enumeration.Status;
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

    private Status status;
}
