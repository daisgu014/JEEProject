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

//    @Modifying
//    @Query("UPDATE Account a " +
//            "SET a.password = :#{#account.password}," +
//            " a.fullname = :#{#account.fullname}," +
//            " a.role = :#{#account.role}," +
//            " a.address = :#{#account.address}," +
//            " a.email = :#{#account.email}," +
//            " a.phone =  :#{#account.phone}" +
//            " WHERE a.id = :#{#account.id}" )
//    void update (@Param("account") Account account);
    @Modifying
    @Query("UPDATE Account a " +
            "SET a.deleteAt = :#{#account.deleteAt} WHERE a.id = :#{#account.id}")
    void delete (@Param("account") Account account);
}
