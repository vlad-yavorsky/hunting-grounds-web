package ua.vlad.hg.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_id_seq")
    @SequenceGenerator(name = "region_id_seq", sequenceName = "region_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region parentRegion;

    @NotBlank
    private String name;

}
