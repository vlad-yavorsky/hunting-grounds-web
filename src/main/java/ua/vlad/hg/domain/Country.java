package ua.vlad.hg.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_seq")
    @SequenceGenerator(name = "country_id_seq", sequenceName = "country_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 3)
    // todo: validate pattern "[0-9]{3}"
    private String isoNumber; // ISO 3166-1 - numeric

    @NotBlank
    @Column(nullable = false, length = 3)
    // todo: validate pattern "[A-Z]{3}"
    private String isoAlpha3Code; // ISO 3166-1 - Alpha-3
}
