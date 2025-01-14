package com.ucc.vacCauca.repository;

import com.ucc.vacCauca.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> getByEmail(String email);
}
