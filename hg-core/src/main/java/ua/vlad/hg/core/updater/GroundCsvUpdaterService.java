package ua.vlad.hg.core.updater;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.core.entity.Ground;
import ua.vlad.hg.core.service.GroundService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroundCsvUpdaterService {

    private final GroundService groundService;
    private final GroundCsvDtoMapper groundCsvDtoMapper;

    private static final DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSS");

    public static final String TO_PROCESS_DIR = "dbdata\\to_process";
    public static final String PROCESSED_DIR = "dbdata\\processed";
    public static final String WEB_DIR = "dbdata\\web";

    public void update(MultipartFile multipartFile) throws IOException {
        Files.createDirectories(Paths.get(WEB_DIR));
        Path filePath = Paths.get(WEB_DIR + "\\web-update-" + timeStampPattern.format(LocalDateTime.now()) + ".csv");
        File file = new File(filePath.toString());
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        batchUpdate(filePath, "web-update");
    }

    public void create(MultipartFile multipartFile) throws IOException {
        Files.createDirectories(Paths.get(WEB_DIR));
        Path outputPath = Paths.get(WEB_DIR + "\\web-create-" + timeStampPattern.format(LocalDateTime.now()) + ".csv");
        File file = new File(outputPath.toString());
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        batchCreate(outputPath, "web-create");
    }

    public void runCreateJob() throws IOException {
        Files.createDirectories(Paths.get(PROCESSED_DIR));
        List<Path> paths = Files.walk(Paths.get(TO_PROCESS_DIR), 1)
                .filter(p -> p.getFileName().toString().startsWith("create") && p.getFileName().toString().endsWith(".csv"))
                .collect(Collectors.toList());
        for (Path path : paths) {
            batchCreate(path, "batch-create");
        }
    }

    public void runUpdateJob() throws IOException {
        List<Path> paths = Files.walk(Paths.get(TO_PROCESS_DIR), 1)
                .filter(p -> p.getFileName().toString().startsWith("update") && p.getFileName().toString().endsWith(".csv"))
                .collect(Collectors.toList());
        for (Path path : paths) {
            batchUpdate(path, "batch-update");
        }
    }

    private void batchCreate(Path path, final String outputFilename) throws IOException {
        FileReader fileReader = new FileReader(path.toString());
        List<GroundCsvDto> list = new CsvToBeanBuilder<GroundCsvDto>(fileReader)
                .withType(GroundCsvDto.class)
                .build()
                .parse();
        fileReader.close();
        groundService.saveAll(groundCsvDtoMapper.toEntity(list));
        Files.createDirectories(Paths.get(PROCESSED_DIR));
        Files.move(path, Paths.get(PROCESSED_DIR + "\\" + outputFilename + "-" + timeStampPattern.format(LocalDateTime.now()) + ".csv"));
    }

    private void batchUpdate(Path path, final String outputFilename) throws IOException {
        FileReader fileReader = new FileReader(path.toString());
        List<GroundCsvDto> list = new CsvToBeanBuilder<GroundCsvDto>(fileReader)
                .withType(GroundCsvDto.class)
                .build()
                .parse();
        fileReader.close();
        Map<String, GroundCsvDto> groundsDtoMap = list.stream()
                .collect(Collectors.toMap(GroundCsvDto::getAlias, groundCsvDto -> groundCsvDto));

        getAndUpdateGrounds(groundsDtoMap);
        Files.createDirectories(Paths.get(PROCESSED_DIR));
        Files.move(path, Paths.get(PROCESSED_DIR + "\\" + outputFilename + "-" + timeStampPattern.format(LocalDateTime.now()) + ".csv"));
    }

    private void getAndUpdateGrounds(Map<String, GroundCsvDto> groundsDtoMap) throws IOException {
        List<Ground> allGroundsByAlias = groundService.findAllFetchFullAddressByAliasIn(groundsDtoMap.keySet());
        for (Ground ground : allGroundsByAlias) {
            updateFieldsIfNotBlank(groundsDtoMap.get(ground.getAlias()), ground);
        }
        groundService.saveAll(allGroundsByAlias);
    }

    private void updateFieldsIfNotBlank(GroundCsvDto groundCsvDto, Ground ground) throws IOException {
        if (StringUtils.isNotBlank(groundCsvDto.getName())) {
            ground.setName(groundCsvDto.getName());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getArea())) {
            ground.setArea(groundCsvDto.getArea());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getKmlPath())) {
            ground.setKml(Files.readString(Path.of(groundCsvDto.getKmlPath())));
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
        if (StringUtils.isNotBlank(groundCsvDto.getLatitude())) {
            ground.getAddress().setLatitude(groundCsvDto.getLatitude());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getLongitude())) {
            ground.getAddress().setLongitude(groundCsvDto.getLongitude());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getInfo())) {
            ground.getAddress().setInfo(groundCsvDto.getInfo());
        }
        if (StringUtils.isNotBlank(groundCsvDto.getCountryId())) {
            ground.getAddress().getCountry().setId(Long.valueOf(groundCsvDto.getCountryId()));
        }
        if (StringUtils.isNotBlank(groundCsvDto.getRegionId())) {
            ground.getAddress().getRegion().setId(Long.valueOf(groundCsvDto.getRegionId()));
        }
        if (StringUtils.isNotBlank(groundCsvDto.getSubRegionId())) {
            ground.getAddress().getSubRegion().setId(Long.valueOf(groundCsvDto.getSubRegionId()));
        }
    }

}
