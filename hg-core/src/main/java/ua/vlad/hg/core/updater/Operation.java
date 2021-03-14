package ua.vlad.hg.core.updater;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.vlad.hg.core.updater.strategy.CsvCreateStrategy;
import ua.vlad.hg.core.updater.strategy.CsvDeleteStrategy;
import ua.vlad.hg.core.updater.strategy.CsvOperationStrategy;
import ua.vlad.hg.core.updater.strategy.CsvUpdateStrategy;

@RequiredArgsConstructor
public enum Operation {

    CREATE(CsvCreateStrategy.INSTANCE),
    UPDATE(CsvUpdateStrategy.INSTANCE),
    DELETE(CsvDeleteStrategy.INSTANCE);

    @Getter
    private final CsvOperationStrategy strategy;

}
