package ua.vlad.hg.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.core.entity.Address;
import ua.vlad.hg.core.entity.Ground;
import ua.vlad.hg.core.exception.ApplicationException;
import ua.vlad.hg.core.exception.ExceptionCode;
import ua.vlad.hg.core.repository.GroundRepository;
import ua.vlad.hg.core.util.KmlDocument;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroundService {

    private final GroundRepository groundRepository;

    public Page<Ground> findAll(Pageable pageable) {
        return groundRepository.findAll(pageable);
    }

    public List<Ground> findAll() {
        return groundRepository.findAll();
    }

    public Ground find(Long id) {
        return groundRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.GROUND_NOT_FOUND, id));
    }

    public Ground findByAlias(String alias) {
        return groundRepository.findByAlias(alias)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.GROUND_NOT_FOUND, alias));
    }

    public Ground create(Ground ground, MultipartFile kmlFile) throws IOException {
        ground.setCreated(new Date());
        ground.getAddress().setType(Address.Type.GROUND);
        ground.setKml(!StringUtils.isEmpty(kmlFile) ? new String(kmlFile.getBytes()) : null);
        return groundRepository.save(ground);
    }

    public void update(Ground ground, MultipartFile kmlFile) throws IOException {
        ground.setCreated(new Date()); // todo: (001) fix created date
        ground.setKml(!StringUtils.isEmpty(kmlFile) ? new String(kmlFile.getBytes()) : null);
        groundRepository.save(ground);
    }

    public void reverseInnerBounds(Long groundId) {
        Ground ground = find(groundId);
        if (!StringUtils.isEmpty(ground.getKml())) {
            ground.setKml(KmlDocument.of(ground.getKml()).reverseInnerBounds());
            groundRepository.save(ground);
        }
    }

    public void delete(Long id) {
        groundRepository.deleteById(id);
    }

}
