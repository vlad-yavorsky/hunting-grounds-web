package ua.vlad.hg.service.strategy;

import ua.vlad.hg.service.GroundService;
import ua.vlad.hg.dto.GroundCsvDto;
import ua.vlad.hg.mapper.GroundCsvDtoMapper;

public interface CsvOperationStrategy {

    String DEFAULT_ERROR_MESSAGE = "[FAILED] Index: {} | Type: {} | Ground: {} | Message: {}";

    void process(GroundService groundService, GroundCsvDtoMapper groundCsvDtoMapper, GroundCsvDto groundCsvDto);

}
