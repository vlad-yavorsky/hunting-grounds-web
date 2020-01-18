package ua.vlad.hg.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreateDto {

    private Long countryId;
    private Long regionId;
    private Long subRegionId;
    private String city;
    private String info;
    private String latitude;
    private String longitude;
    private String street;
    private String zipCode;

}
