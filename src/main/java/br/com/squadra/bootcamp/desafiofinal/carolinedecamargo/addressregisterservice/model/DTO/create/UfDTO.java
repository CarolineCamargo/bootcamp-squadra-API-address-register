package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UfDTO {

    @JsonProperty("codigoUF")
    private Integer id;

    @JsonProperty ("sigla")
    private String initials;

    @JsonProperty ("nome")
    private String name;

    private Integer status;
}
