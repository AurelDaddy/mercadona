package com.example.demo.repository;

import com.example.demo.pojo.UsernotUse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotUseRepository extends CrudRepository<UsernotUse, Long> {
    @Query("SELECT a FROM UsernotUse a")
    List<UsernotUse> findAllUser();


   /* <S extends User> S save(S user);
    Optional<User> findByName(String username);

    */

}
