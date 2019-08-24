package ua.vlad.hg.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ground")
public class Ground {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ground_id_seq")
    @SequenceGenerator(name = "ground_id_seq", sequenceName = "ground_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String alias;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;

    private Double area;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Address address;

    @Column(length = 4000)
    private String description;

    @Column(length = 1048576)
    private String kml;

}
