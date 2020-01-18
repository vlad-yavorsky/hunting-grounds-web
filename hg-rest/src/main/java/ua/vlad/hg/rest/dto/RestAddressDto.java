package ua.vlad.hg.rest.dto;

import lombok.Getter;
import lombok.Setter;
import ua.vlad.hg.core.dto.LatLng;

@Getter
@Setter
public class RestAddressDto {

    private String country;
    private Long regionId;
    private String region;
    private String subRegion;
    private String city;
    private String info;
    private LatLng marker;
    private String street;
    private String zipCode;

}
