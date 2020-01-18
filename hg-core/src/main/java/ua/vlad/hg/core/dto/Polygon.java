package ua.vlad.hg.core.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Polygon {

    private final List<LatLng> outerBounds;
    private final List<List<LatLng>> innerBoundsList;

}
