package com.journal.repositories;

import com.journal.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jonathon lancaster on 1/30/2017.
 */
@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, Integer> {
    UserRole save(UserRole role);

    int countByUsernameAndRole(String username, String role);

    @Modifying
    @Transactional
    @Query(value = "delete from UserRole u where u.username = ?1 and u.role = ?2")
    void deleteUserRoleByUsernameAndRole(String username, String role);

    UserRole findByUserRoleId(int userRoleId);

    List<UserRole> findByUsername(String username);

    List<UserRole> findByRole(String role);
}
