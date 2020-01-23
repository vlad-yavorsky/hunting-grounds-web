package ua.vlad.hg.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
public class Contact {

    @RequiredArgsConstructor
    public enum Type {
        WEBSITE("W"),
        EMAIL("E"),
        MOBILE("M"),
        PHONE("P");

        @Getter
        private final String code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id_seq")
    @SequenceGenerator(name = "contact_id_seq", sequenceName = "contact_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Address address;

    @NotBlank
    private String type;

    @NotBlank
    private String data;

    private String info;

}
