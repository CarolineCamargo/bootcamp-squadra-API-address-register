package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.get.CityOnDistrictDTO;
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
    @SequenceGenerator(name = "city_generator", allocationSize = 1, sequenceName = "SEQUENCE_MUNICIPIO")
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_UF")
    private Uf uf;

    @Column(name = "NOME")
    private String name;

    @Column(name = "STATUS")
    private Integer status;

    public static CityOnDistrictDTO toGetDTO(City city) {
        return CityOnDistrictDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .ufDTO(Uf.toGetDTO(city.getUf()))
                .ufId(city.getUf().getId())
                .build();
    }
}