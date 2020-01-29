package ua.vlad.hg.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroundUpdateDto {

    private Long id;
    private AddressUpdateDto address;
    private String alias;
    private String area;
    private String description;
    private String kml;
    private String name;
    private boolean removeKml;
    private boolean reverseInnerBounds;

}
