package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Provider;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProviderRepository extends CrudRepository<Provider,Integer> {
    @Query("SELECT p FROM Provider p WHERE p.name = :name")
    List<Provider> findByName(@Param("name") String name);

    @Modifying
    @Query("UPDATE Provider p " +
            "SET p.name = :#{#provider.name} WHERE p.id = :#{#provider.id}")
    void update (@Param("provider") Provider provider);
    @Modifying
    @Query("UPDATE Provider p " +
            "SET p.deleteAt = :#{#provider.deleteAt} WHERE p.id = :#{#provider.id}")
    void delete (@Param("provider") Provider provider);
}
