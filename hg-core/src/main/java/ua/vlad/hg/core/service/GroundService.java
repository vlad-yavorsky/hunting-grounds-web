package ua.vlad.hg.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.vlad.hg.core.entity.Address;
import ua.vlad.hg.core.entity.Ground;
import ua.vlad.hg.core.exception.ApplicationException;
import ua.vlad.hg.core.exception.ExceptionCode;
import ua.vlad.hg.core.repository.GroundRepository;
import ua.vlad.hg.core.util.KmlDocument;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class GroundService {

    private final GroundRepository groundRepository;

    public Page<Ground> findAll(Pageable pageable) {
        return groundRepository.findAll(pageable);
    }

    public Ground find(Long id) {
        return groundRepository.findFetchAddressById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.GROUND_NOT_FOUND, id));
    }

    public Ground findByAlias(String alias) {
        return groundRepository.findByAlias(alias)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.GROUND_NOT_FOUND, alias));
    }

    public List<Ground> findAllFetchFullAddressByAliasIn(Set<String> alias) {
        return groundRepository.findAllFetchFullAddressByAliasIn(alias);
    }

    public List<Ground> saveAll(List<Ground> grounds) {
        return groundRepository.saveAll(grounds);
    }

    public Ground create(Ground ground, MultipartFile kmlFile) throws IOException {
        ground.getAddress().setType(Address.Type.GROUND);
        if (!kmlFile.isEmpty()) {
            ground.setKml(new String(kmlFile.getBytes()));
        }
        return groundRepository.save(ground);
    }

    public void update(Ground ground, MultipartFile kmlFile, boolean isRemoveKml, boolean isReverseInnerBounds) throws IOException {
        if (!kmlFile.isEmpty()) {
            ground.setKml(new String(kmlFile.getBytes()));
        } else if (isRemoveKml) {
            ground.setKml(null);
        } else if (isReverseInnerBounds) {
            ground.setKml(KmlDocument.of(ground.getKml()).reverseInnerBounds());
        }
        groundRepository.save(ground);
    }

    public void reverseInnerBounds(Long groundId) {
        Ground ground = find(groundId);
        if (StringUtils.isNotBlank(ground.getKml())) {
            ground.setKml(KmlDocument.of(ground.getKml()).reverseInnerBounds());
            groundRepository.save(ground);
        }
    }

    public void delete(Long id) {
        groundRepository.deleteById(id);
    }

}
