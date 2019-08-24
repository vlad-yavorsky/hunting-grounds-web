package ua.vlad.hg.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @RequiredArgsConstructor
    public enum Type {
        GROUND("G");

        @Getter
        private final String code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    private Integer id;

    @NotBlank
    @Column(length = 1, nullable = false)
    private String type;

    private String zipCode;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Country country;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Region region;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Region subRegion;

    private String city;

    private String street;

    private Double latitude;

    private Double longitude;

    private String info;

}
