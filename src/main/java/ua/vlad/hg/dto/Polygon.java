package ua.vlad.hg.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Polygon {

    private final List<LatLng> outerBounds;
    private final List<List<LatLng>> innerBoundsList;

}
