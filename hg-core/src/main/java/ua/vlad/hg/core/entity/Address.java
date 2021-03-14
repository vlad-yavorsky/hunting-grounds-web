package ua.vlad.hg.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
public class Address {

    @RequiredArgsConstructor
    public enum Type {
        GROUND("G"),
        USER("U");

        @Getter
        private final String code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region subRegion;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    private String city;

    private String info;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String street;

    private Address.Type type;

    private String zipCode;

}
