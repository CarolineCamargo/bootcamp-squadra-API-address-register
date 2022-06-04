package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.enumeration.Status;
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
    private Integer idCity;

    @JsonProperty ("nome")
    private String name;

    private Status status;
}
