package ua.vlad.hg.dto.rest;

import lombok.Getter;
import lombok.Setter;
import ua.vlad.hg.dto.LatLng;

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
