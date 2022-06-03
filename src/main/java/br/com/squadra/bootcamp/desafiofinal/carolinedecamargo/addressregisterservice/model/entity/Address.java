package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("codigoEndereco")
    private Integer id;

    // não seria ao contrário? tabela endereço na tabela pessoa?
    @ManyToOne
    @JoinColumn(name = "CODIGO_PESSOA")
    @JsonProperty("codigoPessoa")
    private Person idPerson;

    @ManyToOne
    @JoinColumn(name = "CODIGO_BAIRRO")
    @JsonProperty("codigoBairro")
    private District idDistrict;

    @Column(name = "NOME_RUA")
    @JsonProperty("nomeRua")
    private String street;

    @Column(name = "NUMERO")
    @JsonProperty("numero")
    private String number;

    @Column(name = "COMPLEMENTO")
    @JsonProperty("complemento")
    private String complement;

    @Column(name = "CEP")
    private String cep;

}