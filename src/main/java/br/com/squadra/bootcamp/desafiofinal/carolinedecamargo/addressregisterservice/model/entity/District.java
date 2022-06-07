package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_BAIRRO")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_generator")
    @SequenceGenerator(name = "district_generator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_BAIRRO")
    @Column(name = "CODIGO_BAIRRO")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private City city;

    @Column(name = "NOME")
    private String name;

    @Column(name = "STATUS")
    private int status;
}