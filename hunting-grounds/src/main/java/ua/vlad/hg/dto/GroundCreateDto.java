package ua.vlad.hg.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GroundCreateDto {

    private AddressCreateDto address;
    private String alias;
    private BigDecimal area;
    private String description;
    private String name;

}
