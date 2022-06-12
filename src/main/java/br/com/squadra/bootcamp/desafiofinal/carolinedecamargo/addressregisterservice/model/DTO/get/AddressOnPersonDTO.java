package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressOnPersonDTO {

    @JsonProperty("codigoEndereco")
    private Integer id;

    @JsonProperty("codigoBairro")
    private Integer districtId;

    @JsonProperty("codigoPessoa")
    private Integer personId;

    @JsonProperty("nomeRua")
    private String street;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("complemento")
    private String complement;

    private String cep;

    @JsonProperty("bairro")
    private DistrictOnAddressDTO districtOnAddressDTO;

}
