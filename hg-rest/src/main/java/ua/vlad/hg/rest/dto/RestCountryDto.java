package ua.vlad.hg.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestCountryDto {

    private Long id;
    private String isoNumber;
    private String isoAlpha3Code;
    private String name;

}
