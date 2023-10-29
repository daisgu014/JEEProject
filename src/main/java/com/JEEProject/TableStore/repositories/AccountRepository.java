package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {
    @Modifying
    @Query("UPDATE Account a " +
            "SET a.password = :#{#account.password}," +
            " a.fullname = :#{#account.fullname}," +
            " a.role = :#{#account.role}," +
            " a.address = :#{#account.address}," +
            " a.email = :#{#account.email}  " +
            "WHERE a.id = :#{#account.id}")
    void update (@Param("account") Account account);
//    @Param("newPassword") String newPassword,
//    @Param("newFullname") String newFullname,
//    @Param("newRole") String newRole,
//    @Param("newAddress") String newAddress,
//    @Param("newEmail") String newEmail,
//    @Param("id") int id
}
