package ua.vlad.hg.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class LatLng {

    private final BigDecimal lat;
    private final BigDecimal lng;

}
