package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_UF")
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uf_generator")
    @SequenceGenerator(name = "uf_generator", initialValue = 1, allocationSize = 1, sequenceName = "SEQUENCE_UF")
    @Column(name = "CODIGO_UF")
    private Integer id;

    @Column(name = "SIGLA")
    private String initials;

    @Column(name = "NOME")
    private String name;

    @Column(name = "STATUS")
    private int status;
}