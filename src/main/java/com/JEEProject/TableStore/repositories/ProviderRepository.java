package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<Provider,Integer> {

}
