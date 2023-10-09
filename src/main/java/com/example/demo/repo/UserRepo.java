package com.example.demo.repo;

import com.example.demo.model.Produit;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    <S extends User> S save(S user);
    Optional<User> findByName(String username);

}
