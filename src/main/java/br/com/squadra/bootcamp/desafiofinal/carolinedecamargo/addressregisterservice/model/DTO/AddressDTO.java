package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.District;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    @JsonProperty("codigoEndereco")
    private Integer id;

    @JsonProperty("codigoBairro")
    private Integer idDistrict;

    @JsonProperty("codigoPessoa")
    private Integer idPerson;

    @JsonProperty("nomeRua")
    private String street;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("complemento")
    private String complement;

    private String cep;

    @JsonProperty("bairro")
    private DistrictDTO districtDTO;

    @JsonProperty("municipio")
    private CityDTO cityDTO;

    @JsonProperty("uf")
    private UfDTO ufDTO;
}
