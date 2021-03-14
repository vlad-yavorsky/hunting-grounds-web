package ua.vlad.hg.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddressDto {

    private Long id;
    private Long countryId;
    private Long regionId;
    private Long subRegionId;
    private String city;
    private String info;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String street;
    private String type;
    private String zipCode;

}
