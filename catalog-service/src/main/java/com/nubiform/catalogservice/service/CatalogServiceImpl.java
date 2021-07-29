package com.nubiform.catalogservice.service;

import com.nubiform.catalogservice.dto.CatalogDto;
import com.nubiform.catalogservice.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<CatalogDto> getAllCatalogs() {
        return catalogRepository.findAll().stream()
                .map(catalogEntity -> modelMapper.map(catalogEntity, CatalogDto.class))
                .collect(Collectors.toList());
    }
}
