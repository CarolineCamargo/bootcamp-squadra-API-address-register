package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {

    @JsonProperty("codigoPessoa")
    private Integer id;

    @JsonProperty ("nome")
    private String name;

    @JsonProperty("sobrenome")
    private String lastName;

    @JsonProperty("idade")
    private Integer age;

    private String login;

    @JsonProperty("senha")
    private String password;

    private Integer status;

    @JsonProperty("enderecos")
    private List<AddressDTO> addressDTOs;
}
