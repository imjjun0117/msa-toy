package com.example.catalogservice.service;

import com.example.catalogservice.vo.CatalogEntity;

public interface CatalogSercvice {

    Iterable<CatalogEntity> getAllCatalogs();

}
