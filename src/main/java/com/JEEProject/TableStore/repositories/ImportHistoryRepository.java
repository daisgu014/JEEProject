package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.ImportHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportHistoryRepository extends CrudRepository<ImportHistory,Integer> {
}
