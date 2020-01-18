package ua.vlad.hg.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroundCreateDto {

    private AddressCreateDto address;
    private String alias;
    private String area;
    private String description;
    private String name;

}
