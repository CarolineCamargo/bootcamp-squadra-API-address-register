package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_PESSOA")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_PESSOA")
    @Column(name = "CODIGO_PESSOA")
    @JsonProperty("codigoPessoa")
    private Integer id;

    @Column(name = "NOME")
    @JsonProperty ("nome")
    private String name;

    @Column(name = "SOBRENOME")
    @JsonProperty("sobrenome")
    private String lastName;

    @Column(name = "IDADE")
    @JsonProperty("idade")
    private Integer age;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    @JsonProperty("senha")
    private String password;

    @Column(name = "STATUS")
    private Status status;
}