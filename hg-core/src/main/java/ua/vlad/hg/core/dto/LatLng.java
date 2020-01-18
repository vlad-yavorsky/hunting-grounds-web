package ua.vlad.hg.core.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LatLng {

    private final Double lat;
    private final Double lng;

}
