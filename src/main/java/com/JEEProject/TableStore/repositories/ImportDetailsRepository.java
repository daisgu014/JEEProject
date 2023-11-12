package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.ImportDetails;
import com.JEEProject.TableStore.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportDetailsRepository extends CrudRepository<ImportDetails,Integer> {
    Page<ImportDetails> findAll(Pageable pageable);
}
