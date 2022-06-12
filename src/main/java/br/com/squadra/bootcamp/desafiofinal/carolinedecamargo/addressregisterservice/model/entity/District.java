package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.DTO.get.DistrictOnAddressDTO;
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
    @SequenceGenerator(name = "district_generator", allocationSize = 1, sequenceName = "SEQUENCE_BAIRRO")
    @Column(name = "CODIGO_BAIRRO")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private City city;

    @Column(name = "NOME")
    private String name;

    @Column(name = "STATUS")
    private Integer status;

    public static DistrictOnAddressDTO toGetDTO(District district) {
        return DistrictOnAddressDTO.builder()
                .id(district.getId())
                .cityId(district.getCity().getId())
                .name(district.getName())
                .status(district.getStatus())
                .cityOnDistrictDTO(City.toGetDTO(district.getCity()))
                .build();
    }
}