package ua.vlad.hg.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GroundDto {

    private Long id;
    private AddressDto address;
    private String alias;
    private String area;
    private Date created;
    private String description;
    private String kml;
    private String name;
    private String logoUrl;

}
