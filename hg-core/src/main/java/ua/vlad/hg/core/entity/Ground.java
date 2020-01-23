package ua.vlad.hg.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
@NamedEntityGraph(name = Ground.Graph.ADDRESS, attributeNodes = @NamedAttributeNode(value = "address"))
public class Ground {

    public static final class Graph {
        public static final String ADDRESS = "ground.address";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ground_id_seq")
    @SequenceGenerator(name = "ground_id_seq", sequenceName = "ground_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    private Address address;

    @NotBlank
    private String alias;

    private String area;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") // todo: (001) fix created date
    private Date created;

    private String description;

    private String kml;

    @NotBlank
    private String name;

}
