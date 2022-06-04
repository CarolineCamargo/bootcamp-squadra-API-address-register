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
@Table(name = "TB_BAIRRO")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_generator")
    @SequenceGenerator(name = "district_generator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_BAIRRO")
    @Column(name = "CODIGO_BAIRRO")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private City idCity;

    @Column(name = "NOME")
    private String name;

    @Column(name = "STATUS")
    private Status status;
}