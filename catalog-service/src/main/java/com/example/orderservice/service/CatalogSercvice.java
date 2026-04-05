package com.example.orderservice.service;

import com.example.orderservice.vo.CatalogEntity;

public interface CatalogSercvice {

    Iterable<CatalogEntity> getAllCatalogs();

}
