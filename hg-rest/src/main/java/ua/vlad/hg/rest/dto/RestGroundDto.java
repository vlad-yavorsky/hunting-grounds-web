package ua.vlad.hg.rest.dto;

import lombok.Getter;
import lombok.Setter;
import ua.vlad.hg.core.dto.Polygon;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RestGroundDto {

    private Long id;
    private RestAddressDto address;
    private String alias;
    private String area;
    private Date created;
    private String description;
    private String name;
    private List<Polygon> polygons;

}
