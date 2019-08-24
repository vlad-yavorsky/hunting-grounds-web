package ua.vlad.hg.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_id_seq")
    @SequenceGenerator(name = "region_id_seq", sequenceName = "region_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Country country;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Region parentRegion;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String name;

}
