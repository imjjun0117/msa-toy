package com.example.catalogservice.controller;

import com.example.catalogservice.service.CatalogSercvice;
import com.example.catalogservice.vo.CatalogEntity;
import com.example.catalogservice.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    Environment env;
    CatalogSercvice catalogSercvice;

    @Autowired
    public CatalogController(Environment env, CatalogSercvice catalogSercvice) {
        this.env = env;
        this.catalogSercvice = catalogSercvice;
    }


    @GetMapping("health-check")
    public String status() {
        return String.format("It's Working---local port : %s, server port : %s",
                env.getProperty("local.server.port"), env.getProperty("server.port"));
    }

    @GetMapping("catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> catalogList = catalogSercvice.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        catalogList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
