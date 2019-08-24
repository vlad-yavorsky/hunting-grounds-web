package ua.vlad.hg.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact {

    @RequiredArgsConstructor
    enum Type {
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
    private int id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Address address;

    @NotBlank
    @Column(nullable = false, length = 1)
    private String type;

    @NotBlank
    @Column(nullable = false)
    private String data;

    private String info;

}
