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
@Table(name = "TB_MUNICIPIO")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_generator")
    @SequenceGenerator(name = "city_generator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_MUNICIPIO")
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private Integer idUf;

    @Column(name = "NOME")
    private String name;

    @Column(name = "STATUS")
    private Status status;

}