package com.example.demo.repository;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT a FROM User a")
    List<User> findAllUser();


   /* <S extends User> S save(S user);
    Optional<User> findByName(String username);

    */

}
