package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByUsername(String username);
    Account findByPhone(String phone);
    Account findByEmail(String email);

}
