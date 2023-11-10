package com.JEEProject.TableStore.Auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserAuthRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);
  Optional<User> findByPhone(String Phone);

}
