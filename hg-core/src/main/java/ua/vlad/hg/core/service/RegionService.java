package ua.vlad.hg.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vlad.hg.core.entity.Country;
import ua.vlad.hg.core.entity.Region;
import ua.vlad.hg.core.exception.ApplicationException;
import ua.vlad.hg.core.exception.ExceptionCode;
import ua.vlad.hg.core.repository.CountryRepository;
import ua.vlad.hg.core.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionService {

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    public List<Region> findAllByCountryIdAndParentRegionIsNull(Long countryId) {
        return regionRepository.findAllByCountryIdAndParentRegionIsNull(countryId, Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Region> findAllByParentRegionId(Long parentRegionId) {
        return regionRepository.findAllByParentRegionId(parentRegionId, Sort.by(Sort.Direction.ASC, "name"));
    }

    public Region find(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.REGION_NOT_FOUND, id));
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public void delete(Long id) {
        regionRepository.deleteById(id);
    }

}
