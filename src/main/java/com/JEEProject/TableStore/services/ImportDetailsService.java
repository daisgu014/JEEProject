package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Auth.user.User;
import com.JEEProject.TableStore.Model.ImportDetails;
import com.JEEProject.TableStore.Model.ImportHistory;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.ImportDetailsRepository;
import com.JEEProject.TableStore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ImportDetailsService {
    private final ImportDetailsRepository importDetailsRepository;
    private final ProductRepository productRepository;
    public void saveImportDetail(ImportHistory importHistory, Product product, Integer qty){
        if(productRepository.findById(product.getId()).isPresent()){
            product.setInStock(product.getInStock()+qty);
            productRepository.save(product);
            importDetailsRepository.save(ImportDetails.builder()
                    .importHistory(importHistory)
                    .productId(product)
                    .qty(qty)
                    .build());
        }
    }
    public void saveImportDetails(ImportHistory importHistory, HashMap<Integer, Integer > productQtys){
            productQtys.forEach((key, value) -> {
                if(productRepository.findById(key).isPresent()){
                    Product product = productRepository.findById(key).get();
                    product.setInStock(product.getInStock()+value);
                    productRepository.save(product);
                    importDetailsRepository.save(ImportDetails.builder()
                            .importHistory(importHistory)
                            .productId(product)
                            .qty(value)
                            .build());
                }
            });
    }
    public Page<ImportDetails> getImport(Pageable pageable){
        return importDetailsRepository.findAll(pageable);
    }

}
