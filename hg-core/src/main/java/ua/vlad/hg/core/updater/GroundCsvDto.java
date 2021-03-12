package ua.vlad.hg.core.updater;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import ua.vlad.hg.core.entity.Address;

@Getter
@Setter
public class GroundCsvDto {

    @CsvBindByName(required = true)
    private String name;

    @CsvBindByName(required = true)
    private String alias;

    @CsvBindByName
    private String area;

    @CsvBindByName
    private String kmlPath;

    @CsvBindByName
    private String description;

    @CsvBindByName
    private String city;

    @CsvBindByName
    private String street;

    @CsvBindByName
    private String zipCode;

    @CsvBindByName
    private String latitude;

    @CsvBindByName
    private String longitude;

    @CsvBindByName
    private String info;

    @CsvBindByName(required = true)
    private String countryId;

    @CsvBindByName(required = true)
    private String regionId;

    @CsvBindByName
    private String subRegionId;

    private Address.Type type = Address.Type.GROUND;

    @Override
    public String toString() {
        return "GroundCsvDto{" +
                "name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", area='" + area + '\'' +
                ", kml='" + kmlPath + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zip_code='" + zipCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", info='" + info + '\'' +
                ", country_id='" + countryId + '\'' +
                ", region_id='" + regionId + '\'' +
                ", sub_region_id='" + subRegionId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
