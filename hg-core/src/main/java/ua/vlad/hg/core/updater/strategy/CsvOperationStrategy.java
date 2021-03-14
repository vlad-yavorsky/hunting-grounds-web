package ua.vlad.hg.core.updater.strategy;

import ua.vlad.hg.core.service.GroundService;
import ua.vlad.hg.core.updater.dto.GroundCsvDto;
import ua.vlad.hg.core.updater.mapper.GroundCsvDtoMapper;

public interface CsvOperationStrategy {

    String DEFAULT_ERROR_MESSAGE = "[FAILED] Index: {} | Type: {} | Ground: {} | Message: {}";

    void process(GroundService groundService, GroundCsvDtoMapper groundCsvDtoMapper, GroundCsvDto groundCsvDto);

}
