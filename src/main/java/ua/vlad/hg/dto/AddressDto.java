package ua.vlad.hg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private String zipCode;
    private String country;
    private String regionId;
    private String regionName;
    private String subRegion;
    private String city;
    private String street;
    private LatLng marker;
    private String info;

}
