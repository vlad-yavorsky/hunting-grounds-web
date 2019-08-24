package ua.vlad.hg.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.domain.Region;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findAllByCountryIdAndParentRegionIsNull(Long countryId, Sort sort);

    List<Region> findAllByParentRegionId(Long parentRegionId, Sort sort);

}
