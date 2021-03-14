package ua.vlad.hg.core.updater.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.core.service.GroundService;
import ua.vlad.hg.core.updater.dto.GroundCsvDto;
import ua.vlad.hg.core.updater.mapper.GroundCsvDtoMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroundCsvUpdaterService {

    private final GroundService groundService;
    private final GroundCsvDtoMapper groundCsvDtoMapper;

    private static final DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSS");

    public static final String BATCH_DIR = "batch\\";
    public static final String PROCESSED_DIR = BATCH_DIR + "processed\\";
    public static final String TO_PROCESS_DIR = BATCH_DIR + "to_process\\";
    public static final String WEB_DIR = BATCH_DIR + "web\\";
    public static final String CSV = ".csv";

    public List<GroundCsvDto> batch(MultipartFile multipartFile) throws IOException {
        Files.createDirectories(Paths.get(WEB_DIR));
        Path outputPath = Paths.get(WEB_DIR + "web-batch-" + timeStampPattern.format(LocalDateTime.now()) + CSV);
        File file = new File(outputPath.toString());
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        List<GroundCsvDto> list = getGroundsCsvDto(outputPath);
        processOneByOne(list);
        renameAndMoveFileTo(outputPath, "web-batch");
        return list;
    }

    public void batchJob() throws IOException {
        List<Path> paths = Files.walk(Paths.get(TO_PROCESS_DIR), 1)
                .filter(this::filenamePattern)
                .collect(Collectors.toList());
        for (Path path : paths) {
            List<GroundCsvDto> list = getGroundsCsvDto(path);
            processOneByOne(list);
            renameAndMoveFileTo(path, "batch");
        }
    }

    private boolean filenamePattern(Path p) {
        return p.getFileName().toString().endsWith(CSV);
    }

    private List<GroundCsvDto> getGroundsCsvDto(Path path) throws IOException {
        FileReader fileReader = new FileReader(path.toString());
        List<GroundCsvDto> list = parseFile(fileReader);
        fileReader.close();
        return list;
    }

    private List<GroundCsvDto> parseFile(FileReader fileReader) {
        return new CsvToBeanBuilder<GroundCsvDto>(fileReader)
                .withType(GroundCsvDto.class)
                .build()
                .parse();
    }

    private void processOneByOne(List<GroundCsvDto> list) {
        for (int i = 0, listSize = list.size(); i < listSize; i++) {
            GroundCsvDto groundCsvDto = list.get(i);
            groundCsvDto.setIndex(i + 1);
            groundCsvDto.getOperation().getStrategy()
                    .process(groundService, groundCsvDtoMapper, groundCsvDto);
        }
    }

    private void renameAndMoveFileTo(Path path, String outputFilename) throws IOException {
        Files.createDirectories(Paths.get(PROCESSED_DIR));
        Files.move(path, Paths.get(PROCESSED_DIR + outputFilename + "-" + timeStampPattern.format(LocalDateTime.now()) + CSV));
    }

}
