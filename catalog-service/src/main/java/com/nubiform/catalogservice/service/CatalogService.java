package com.nubiform.catalogservice.service;

import com.nubiform.catalogservice.dto.CatalogDto;

import java.util.List;

public interface CatalogService {

    List<CatalogDto> getAllCatalogs();
}
