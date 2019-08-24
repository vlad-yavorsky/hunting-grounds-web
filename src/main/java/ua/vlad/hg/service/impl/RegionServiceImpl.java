package ua.vlad.hg.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.vlad.hg.domain.Region;
import ua.vlad.hg.dto.CountryDto;
import ua.vlad.hg.dto.RegionDto;
import ua.vlad.hg.mapper.CountryMapper;
import ua.vlad.hg.mapper.RegionMapper;
import ua.vlad.hg.repository.CountryRepository;
import ua.vlad.hg.repository.RegionRepository;
import ua.vlad.hg.service.RegionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final CountryMapper countryMapper;
    private final RegionMapper regionMapper;

    @Override
    public void delete(Region region) {
        regionRepository.delete(region);
    }

    @Override
    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public RegionDto findById(Long id) {
        RegionDto regionDto = null;
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()) {
            regionDto = regionMapper.toDto(region.get());
        }
        return regionDto;
    }

    @Override
//    @CacheEvict(value = "regions", key = "#result.id", allEntries = true, condition = "#result != null")
    public Region save(Region region) {
        return regionRepository.save(region);
    }


    @Override
    public List<CountryDto> findAllCountries() {
        return countryRepository.findAll().stream().map(countryMapper::toDto).collect(Collectors.toList());
    }


    @Override
//    @Cacheable(value = "regions")
    public List<RegionDto> findAllByCountryIdAndParentRegionIsNull(Long parentRegionId) {
        return regionRepository.findAllByCountryIdAndParentRegionIsNull(parentRegionId, new Sort(Sort.Direction.ASC, "name"))
                .stream().map(regionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RegionDto> findAllByParentRegionId(Long parentRegionId) {
        return regionRepository.findAllByParentRegionId(parentRegionId, new Sort(Sort.Direction.ASC, "name"))
                .stream().map(regionMapper::toDto).collect(Collectors.toList());
    }
}
