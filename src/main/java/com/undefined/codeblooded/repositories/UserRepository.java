package com.undefined.codeblooded.repositories;

import com.undefined.codeblooded.models.ApplicationUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(
            "select u from ApplicationUser u where u.email = ?1"
    )
    ApplicationUser getByEmail(String email);

}
