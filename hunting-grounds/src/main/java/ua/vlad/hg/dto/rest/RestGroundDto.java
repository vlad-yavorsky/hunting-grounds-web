package ua.vlad.hg.dto.rest;

import lombok.Getter;
import lombok.Setter;
import ua.vlad.hg.dto.Polygon;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RestGroundDto {

    private Long id;
    private RestAddressDto address;
    private String alias;
    private BigDecimal area;
    private Date created;
    private String description;
    private String name;
    private List<Polygon> polygons;

}
