package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.get;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create.UfDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityOnDistrictDTO {

    @JsonProperty("codigoMunicipio")
    private Integer id;

    @JsonProperty("codigoUF")
    private Integer ufId;

    @JsonProperty ("nome")
    private String name;

    private int status;

    @JsonProperty("uf")
    private UfDTO ufDTO;
}
