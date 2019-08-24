package ua.vlad.hg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
