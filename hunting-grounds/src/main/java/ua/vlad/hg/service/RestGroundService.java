package ua.vlad.hg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vlad.hg.entity.Ground;
import ua.vlad.hg.exception.ApplicationException;
import ua.vlad.hg.exception.ExceptionCode;
import ua.vlad.hg.repository.GroundRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestGroundService {

    private final GroundRepository groundRepository;

    public List<Ground> findAll() {
        return groundRepository.findAllFetchFullAddressBy();
    }

    public Ground find(Long id) {
        return groundRepository.findFetchFullAddressById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.GROUND_NOT_FOUND, id));
    }

}
