package ua.vlad.hg.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GroundDto {

    private Long id;
    private String name;
    private String alias;
    private Date created;
    private Double area;
    private AddressDto address;
    private String description;
    private List<Polygon> polygons;

}
