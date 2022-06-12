package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create;


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
}
