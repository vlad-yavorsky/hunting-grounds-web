package ua.vlad.hg.core.updater.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import ua.vlad.hg.core.entity.Ground;
import ua.vlad.hg.core.exception.ApplicationException;
import ua.vlad.hg.core.exception.ExceptionCode;
import ua.vlad.hg.core.service.GroundService;
import ua.vlad.hg.core.updater.BatchItemStatus;
import ua.vlad.hg.core.updater.dto.GroundCsvDto;
import ua.vlad.hg.core.updater.mapper.GroundCsvDtoMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class CsvUpdateStrategy implements CsvOperationStrategy {

    public static final CsvUpdateStrategy INSTANCE = new CsvUpdateStrategy();

    private CsvUpdateStrategy() {
    }

    @Transactional
    @Override
    public void process(GroundService groundService, GroundCsvDtoMapper groundCsvDtoMapper, GroundCsvDto groundCsvDto) {
        try {
            Ground groundByAlias = groundService.findFetchFullAddressByAlias(groundCsvDto.getAlias());
            updateFieldsIfNotBlank(groundCsvDto, groundByAlias);
            groundService.save(groundByAlias);
            groundCsvDto.setItemStatus(BatchItemStatus.success());
        } catch (Exception e) {
            groundCsvDto.setItemStatus(BatchItemStatus.error(e.getMessage()));
            log.info(DEFAULT_ERROR_MESSAGE, groundCsvDto.getIndex(), groundCsvDto.getOperation(), groundCsvDto.getAlias(), e.getMessage());
        }
    }

    private void updateFieldsIfNotBlank(GroundCsvDto groundCsvDto, Ground ground) {
        if (StringUtils.isNotBlank(groundCsvDto.getName())) {
            ground.setName(groundCsvDto.getName());
        }
        if (groundCsvDto.getArea() != null) {
            ground.setArea(groundCsvDto.getArea());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getKmlPath())) {
            try {
                ground.setKml(Files.readString(Path.of(groundCsvDto.getKmlPath())));
            } catch (IOException e) {
                throw new ApplicationException(ExceptionCode.ERROR_READING_FILE, groundCsvDto.getKmlPath());
            }
        }
        if (StringUtils.isNotBlank(groundCsvDto.getDescription())) {
            ground.setDescription(groundCsvDto.getDescription());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getCity())) {
            ground.getAddress().setCity(groundCsvDto.getCity());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getStreet())) {
            ground.getAddress().setStreet(groundCsvDto.getStreet());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getZipCode())) {
            ground.getAddress().setZipCode(groundCsvDto.getZipCode());
        }
        if (groundCsvDto.getLatitude() != null) {
            ground.getAddress().setLatitude(groundCsvDto.getLatitude());
        }
        if (groundCsvDto.getLongitude() != null) {
            ground.getAddress().setLongitude(groundCsvDto.getLongitude());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getInfo())) {
            ground.getAddress().setInfo(groundCsvDto.getInfo());
        }
        if (groundCsvDto.getCountryId() != null) {
            ground.getAddress().getCountry().setId(groundCsvDto.getCountryId());
        }
        if (groundCsvDto.getRegionId() != null) {
            ground.getAddress().getRegion().setId(groundCsvDto.getRegionId());
        }
        if (groundCsvDto.getSubRegionId() != null) {
            ground.getAddress().getSubRegion().setId(groundCsvDto.getSubRegionId());
        }
    }

}
