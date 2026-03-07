package ua.vlad.hg.service.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import ua.vlad.hg.exception.ApplicationException;
import ua.vlad.hg.exception.ExceptionCode;
import ua.vlad.hg.service.GroundService;
import ua.vlad.hg.dto.BatchItemStatus;
import ua.vlad.hg.dto.GroundCsvDto;
import ua.vlad.hg.mapper.GroundCsvDtoMapper;

@Slf4j
public class CsvCreateStrategy implements CsvOperationStrategy {

    public static final CsvCreateStrategy INSTANCE = new CsvCreateStrategy();

    private CsvCreateStrategy() {
    }

    @Transactional
    @Override
    public void process(GroundService groundService, GroundCsvDtoMapper groundCsvDtoMapper, GroundCsvDto groundCsvDto) {
        try {
            validateRequiredFields(groundCsvDto);
            if (groundService.existsByAlias(groundCsvDto.getAlias())) {
                throw new ApplicationException(ExceptionCode.ALIAS_IS_NOT_UNIQUE, groundCsvDto.getAlias());
            }
            groundService.save(groundCsvDtoMapper.toEntity(groundCsvDto));
            groundCsvDto.setItemStatus(BatchItemStatus.success());
        } catch (Exception e) {
            groundCsvDto.setItemStatus(BatchItemStatus.error(e.getMessage()));
            log.info(DEFAULT_ERROR_MESSAGE, groundCsvDto.getIndex(), groundCsvDto.getOperation(), groundCsvDto.getAlias(), e.getMessage());
        }
    }

    private void validateRequiredFields(GroundCsvDto groundCsvDto) {
        if (StringUtils.isBlank(groundCsvDto.getName())) {
            throw new ApplicationException(ExceptionCode.REQUIRED_FIELD_IS_EMPTY, "name");
        }
        if (groundCsvDto.getCountryId() == null) {
            throw new ApplicationException(ExceptionCode.REQUIRED_FIELD_IS_EMPTY, "countryId");
        }
        if (groundCsvDto.getRegionId() == null) {
            throw new ApplicationException(ExceptionCode.REQUIRED_FIELD_IS_EMPTY, "regionId");
        }
    }

}
