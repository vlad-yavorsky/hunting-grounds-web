package ua.vlad.hg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDto {

    private Long id;
    private String name;
    private String isoNumber;
    private String isoAlpha3Code;

}
