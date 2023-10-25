package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.Model.ProductSearchCriteria;
import com.JEEProject.TableStore.repositories.CatalogRepository;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public Iterable<Product> findAllProducts() {
        return catalogRepository.findAll();
    }
    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    public Optional<Product> findProductByID(Integer ProductID) {
        return catalogRepository.findById(ProductID);
    }
<<<<<<< HEAD

=======
    public Page<Product> findProductsNotDeleted(Pageable pageable) {
        return catalogRepository.findProductsNotDeleted(pageable);
    }
    public Iterable<String> findAllColors() {
        return catalogRepository.findAllColors();
    }

    public Page<Product> searchProducts(ProductSearchCriteria criteria, Pageable pageable) {
        return catalogRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + criteria.getName() + "%"));
            }

            if (criteria.getCategoryID() != null && !criteria.getCategoryID().isEmpty()) {
//                List<Integer> categoryIdsAsInt = criteria.getCategoryID().stream()
//                        .map(Integer::parseInt)
//                        .collect(Collectors.toList());
//                predicates.add(root.get("category").get("id").in(categoryIdsAsInt));

                predicates.add(root.get("category").get("id").in(criteria.getCategoryID()));
                // Sử dụng `get` để truy cập thuộc tính `Category` của `Product`
//                Join<Product, Category> categoryJoin = root.join("category");
//                predicates.add(categoryJoin.get("id").in(criteria.getCategoryID()));
            }

            if (criteria.getMinPrice() != null && !criteria.getMinPrice().isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(criteria.getMinPrice())));
            }

            if (criteria.getMaxPrice() != null && !criteria.getMaxPrice().isEmpty()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), Double.parseDouble(criteria.getMaxPrice())));
            }

            if (criteria.getColors() != null && !criteria.getColors().isEmpty()) {
                predicates.add(root.get("color").in(criteria.getColors()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
>>>>>>> main
}
