package ua.vlad.hg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
