package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CatalogService {
    @Autowired
    private CatalogRepository catalogRepository;
}
