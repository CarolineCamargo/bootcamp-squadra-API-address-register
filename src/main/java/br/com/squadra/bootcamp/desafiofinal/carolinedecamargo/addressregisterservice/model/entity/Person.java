package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

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
    private int status;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> address;
}