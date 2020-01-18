package ua.vlad.hg.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.core.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
