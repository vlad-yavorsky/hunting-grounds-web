package ua.vlad.hg.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import ua.vlad.hg.dto.LatLng;
import ua.vlad.hg.dto.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KmlDocument {

    private static final class Tag {
        private static final String PLACEMARK = "Placemark";
        private static final String MULTI_GEOMETRY = "MultiGeometry";
        private static final String POLYGON = "Polygon";
        private static final String OUTER_BOUNDARY_IS = "outerBoundaryIs";
        private static final String INNER_BOUNDARY_IS = "innerBoundaryIs";
        private static final String COORDINATES = "coordinates";
    }

    private final Document document;

    public KmlDocument(String kml) {
        document = Jsoup.parse(kml, "", Parser.xmlParser());
    }

    public List<Polygon> extractPolygons() {
        List<Polygon> polygons = new ArrayList<>();
        document.select(Tag.PLACEMARK).forEach(placemark -> placemark.childNodes().forEach(node -> {
            if (node instanceof Element) {
                extractPolygons((Element) node, polygons);
            }
        }));
        return polygons;
    }

    private void extractPolygons(Element geometry, List<Polygon> polygons) {
        switch (geometry.tag().getName()) {
            case Tag.MULTI_GEOMETRY:
                geometry.childNodes().forEach(node -> {
                    if (node instanceof Element) {
                        extractPolygons((Element) node, polygons);
                    }
                });
                break;
            case Tag.POLYGON:
                polygons.add(createPolygon(geometry));
                break;
        }
    }

    private Polygon createPolygon(Element geometry) {
        List<LatLng> outerBounds = Collections.emptyList();
        List<List<LatLng>> innerBoundsList = null;
        for (Node node : geometry.childNodes()) {
            if (node instanceof Element) {
                Element element = (Element) node;
                switch (element.tag().getName()) {
                    case Tag.OUTER_BOUNDARY_IS:
                        outerBounds = extractBounds(element);
                        break;
                    case Tag.INNER_BOUNDARY_IS:
                        if (innerBoundsList == null) {
                            innerBoundsList = new ArrayList<>();
                        }
                        innerBoundsList.add(extractBounds(element));
                        break;
                }
            }
        }
        return new Polygon(outerBounds, innerBoundsList);
    }

    private List<LatLng> extractBounds(Element element) {
        List<String> coordinatesStrings = Arrays.asList(element.select(Tag.COORDINATES).first().text().split(" "));
        List<LatLng> latLngList = new ArrayList<>();
        coordinatesStrings.forEach(coordinatesString -> {
            List<String> coordinates = Arrays.asList(coordinatesString.split(","));
            latLngList.add(new LatLng(Double.parseDouble(coordinates.get(1)), Double.parseDouble(coordinates.get(0))));
        });
        return latLngList;
    }

    public String reverseInnerBounds() {
        document.select(Tag.INNER_BOUNDARY_IS).forEach(element -> {
            Element coordinatesElement = element.select(Tag.COORDINATES).first();
            List<String> coordinatesStrList = Arrays.asList(coordinatesElement.text().split(" "));
            Collections.reverse(coordinatesStrList);
            coordinatesElement.text(String.join(" ", coordinatesStrList));
        });
        return document.toString();
    }
}
