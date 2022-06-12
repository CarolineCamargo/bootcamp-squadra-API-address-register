package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.create.PersonDTO;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.get.PersonGetDTO;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_PESSOA")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", allocationSize = 1, sequenceName = "SEQUENCE_PESSOA")
    @Column(name = "CODIGO_PESSOA")
    private Integer id;

    @Column(name = "NOME")
    private String name;

    @Column(name = "SOBRENOME")
    private String lastName;

    @Column(name = "IDADE")
    private Integer age;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String password;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> address;

    public static PersonDTO toDTO(Person person){
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .lastName(person.getLastName())
                .age(person.getAge())
                .login(person.getLogin())
                .password(person.getPassword())
                .status(person.getStatus())
                .addressDTOs(new ArrayList<>())
                .build();
    }

    public static PersonGetDTO toDTOWithAddress(Person person){
        return PersonGetDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .lastName(person.getLastName())
                .age(person.getAge())
                .login(person.getLogin())
                .password(person.getPassword())
                .status(person.getStatus())
                .addressDTOs(person.getAddress().stream()
                        .map(Address::toGetDTO).collect(Collectors.toList()))
                .build();
    }

    public void setAddress(List<Address> address) {
        if(!CollectionUtils.isEmpty(this.address)) {
            this.address.clear();
        }
        this.address = address;
    }
}