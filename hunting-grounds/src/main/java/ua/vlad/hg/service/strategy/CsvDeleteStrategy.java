package ua.vlad.hg.service.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ua.vlad.hg.exception.ApplicationException;
import ua.vlad.hg.exception.ExceptionCode;
import ua.vlad.hg.service.GroundService;
import ua.vlad.hg.dto.BatchItemStatus;
import ua.vlad.hg.dto.GroundCsvDto;
import ua.vlad.hg.mapper.GroundCsvDtoMapper;

@Slf4j
public class CsvDeleteStrategy implements CsvOperationStrategy {

    public static final CsvDeleteStrategy INSTANCE = new CsvDeleteStrategy();

    private CsvDeleteStrategy() {
    }

    @Transactional
    @Override
    public void process(GroundService groundService, GroundCsvDtoMapper groundCsvDtoMapper, GroundCsvDto groundCsvDto) {
        try {
            if (!groundService.existsByAlias(groundCsvDto.getAlias())) {
                throw new ApplicationException(ExceptionCode.GROUND_NOT_FOUND, groundCsvDto.getAlias());
            }
            groundService.deleteByAlias(groundCsvDto.getAlias());
            groundCsvDto.setItemStatus(BatchItemStatus.success());
        } catch (Exception e) {
            groundCsvDto.setItemStatus(BatchItemStatus.error(e.getMessage()));
            log.info(DEFAULT_ERROR_MESSAGE, groundCsvDto.getIndex(), groundCsvDto.getOperation(), groundCsvDto.getAlias(), e.getMessage());
        }
    }

}
