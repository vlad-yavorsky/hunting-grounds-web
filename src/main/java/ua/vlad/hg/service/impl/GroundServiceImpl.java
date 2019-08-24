package ua.vlad.hg.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.vlad.hg.domain.Address;
import ua.vlad.hg.domain.Ground;
import ua.vlad.hg.dto.GroundDto;
import ua.vlad.hg.mapper.GroundMapper;
import ua.vlad.hg.repository.AddressRepository;
import ua.vlad.hg.repository.GroundRepository;
import ua.vlad.hg.service.GroundService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroundServiceImpl implements GroundService {

    private final AddressRepository addressRepository;
    private final GroundRepository groundRepository;
    private final GroundMapper groundMapper;

    @Override
    public void delete(Ground user) {
        groundRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        groundRepository.deleteById(id);
    }

    @Override
    public GroundDto findById(Long id) {
        GroundDto groundDto = new GroundDto();
        Optional<Ground> ground = groundRepository.findById(id);
        if (ground.isPresent()) {
            groundDto = groundMapper.toDto(ground.get());
        }
        return groundDto;
    }

    @Override
    public Ground findByAlias(String alias) {
        return groundRepository.findByAlias(alias);
    }

    @Override
    @CacheEvict(value = "grounds", key = "#result.id", allEntries = true, condition = "#result != null")
    public Ground save(Ground ground) {
        if (ground.getAddress().getId() == null) {
            ground.getAddress().setType(Address.Type.GROUND.getCode());
        }
        addressRepository.save(ground.getAddress());
        if (ground.getId() == null) {
            ground.setCreated(new Date());
        }
        return groundRepository.save(ground);
    }

    @Override
    public Page<Ground> findAll(Pageable pageable) {
        return groundRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "grounds")
    public List<GroundDto> findAll() {
        List<Ground> grounds = groundRepository.findAll();
        return grounds.stream().map(groundMapper::toDto).collect(Collectors.toList());
    }
}
