package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_ENDERECO")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
    @SequenceGenerator(name = "address_generator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_ADDRESS")
    @Column(name = "CODIGO_ENDERECO")
    private Integer id;

    @OneToMany
    @JoinColumn(name = "CODIGO_PESSOA")
    private Integer idPerson;

    @ManyToOne
    @JoinColumn(name = "CODIGO_BAIRRO")
    private District idDistrict;

    @Column(name = "NOME_RUA")
    private String street;

    @Column(name = "NUMERO")
    private String number;

    @Column(name = "COMPLEMENTO")
    private String complement;

    @Column(name = "CEP")
    private String cep;

}