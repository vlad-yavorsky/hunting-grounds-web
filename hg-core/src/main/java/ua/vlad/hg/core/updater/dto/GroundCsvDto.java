package ua.vlad.hg.core.updater.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import ua.vlad.hg.core.entity.Address;
import ua.vlad.hg.core.updater.BatchItemStatus;
import ua.vlad.hg.core.updater.Operation;

import java.math.BigDecimal;

@Getter
@Setter
public class GroundCsvDto {

    private int index;
    private BatchItemStatus itemStatus = BatchItemStatus.notProcessed();
    @CsvBindByName(required = true) private Operation operation;
    @CsvBindByName(required = true) private String alias;
    @CsvBindByName private String name;
    @CsvBindByName private BigDecimal area;
    @CsvBindByName private String kmlPath;
    @CsvBindByName private String description;
    @CsvBindByName private String city;
    @CsvBindByName private String street;
    @CsvBindByName private String zipCode;
    @CsvBindByName private BigDecimal latitude;
    @CsvBindByName private BigDecimal longitude;
    @CsvBindByName private String info;
    @CsvBindByName private Long countryId;
    @CsvBindByName private Long regionId;
    @CsvBindByName private Long subRegionId;
    private Address.Type type = Address.Type.GROUND;

}
