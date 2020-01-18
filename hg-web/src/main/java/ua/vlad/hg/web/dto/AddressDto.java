package ua.vlad.hg.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;
    private Long countryId;
    private Long regionId;
    private Long subRegionId;
    private String city;
    private String info;
    private String latitude;
    private String longitude;
    private String street;
    private String type;
    private String zipCode;

}
