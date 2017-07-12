package com.journal.repositories;

import com.journal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * Created by jonathon lancaster on 1/30/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByUserId(int userId);

    User save(User user);

    void delete(User user);

    int countByUsername(String username);


}
