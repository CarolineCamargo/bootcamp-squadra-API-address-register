package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


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

    @JsonProperty("enderecos")
    private List<AddressDTO> addressDTOs;

    private int status;

    public static PersonDTO toDTO(Person person){
        return PersonDTO.builder()
                .addressDTOs(person.getAddress().stream()
                        .map(AddressDTO::toDTO).collect(Collectors.toList()))
                .build();
    }
}
