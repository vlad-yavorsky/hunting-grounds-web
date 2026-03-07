package ua.vlad.hg.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.vlad.hg.service.strategy.CsvCreateStrategy;
import ua.vlad.hg.service.strategy.CsvDeleteStrategy;
import ua.vlad.hg.service.strategy.CsvOperationStrategy;
import ua.vlad.hg.service.strategy.CsvUpdateStrategy;

@RequiredArgsConstructor
public enum Operation {

    CREATE(CsvCreateStrategy.INSTANCE),
    UPDATE(CsvUpdateStrategy.INSTANCE),
    DELETE(CsvDeleteStrategy.INSTANCE);

    @Getter
    private final CsvOperationStrategy strategy;

}
