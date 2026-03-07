package ua.vlad.hg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.vlad.hg.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
