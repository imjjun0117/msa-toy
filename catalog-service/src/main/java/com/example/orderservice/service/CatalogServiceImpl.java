package com.example.orderservice.service;

import com.example.orderservice.jpa.CatalogRepository;
import com.example.orderservice.vo.CatalogEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogSercvice {

    CatalogRepository catalogRepository;

    Environment env;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository, Environment env) {
        this.catalogRepository = catalogRepository;
        this.env = env;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
