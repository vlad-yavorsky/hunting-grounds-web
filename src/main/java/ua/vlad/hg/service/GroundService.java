package ua.vlad.hg.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.vlad.hg.domain.Ground;
import ua.vlad.hg.dto.GroundDto;

import java.util.List;

public interface GroundService {

    Ground save(Ground project);

    void delete(Ground ground);

    void deleteById(Long id);

    GroundDto findById(Long id);

    Ground findByAlias(String alias);

    Page<Ground> findAll(Pageable pageable);

    List<GroundDto> findAll();

}
