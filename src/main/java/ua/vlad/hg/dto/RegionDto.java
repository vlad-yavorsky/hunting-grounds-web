package ua.vlad.hg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDto {

    private Long id;
    private String name;
    private String country;
    private String parentRegion;

}
