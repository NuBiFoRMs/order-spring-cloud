package com.nubiform.catalogservice.controller;

import com.nubiform.catalogservice.service.CatalogService;
import com.nubiform.catalogservice.vo.CatalogResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    private final CatalogService catalogService;

    private final Environment environment;

    private final ModelMapper modelMapper;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Catalog Service on PORT %s", environment.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogResponse>> getCatalogs() {
        return ResponseEntity.ok().body(catalogService.getAllCatalogs().stream()
                .map(catalogDto -> modelMapper.map(catalogDto, CatalogResponse.class))
                .collect(Collectors.toList()));
    }

}
