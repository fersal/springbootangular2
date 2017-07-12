package com.journal.services;

import com.journal.entities.UserRole;
import com.journal.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JLancaster on 3/21/2017.
 */
@Service
public class UserRoleManagerService {

    @Autowired
    private UserRoleRepository repository;

    private static final String standardRole = "ROLE_USER";
    private static final String coachRole = "ROLE_COACH";
    private static final String adminRole = "ROLE_ADMIN";

    public void createDefaultRole(String username) {
        UserRole role = new UserRole();
        role.setUsername(username);
        role.setRole(standardRole);

        repository.save(role);
    }

    public List<UserRole> getRolesByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean enableAdmin(String username) {
        return enableRole(username, adminRole);
    } //TODO: make enabling admin also enable coach

    public boolean enableCoach(String username) {
        return enableRole(username, coachRole);
    }

    public boolean disableAdmin(String username) {
        return disableRole(username, adminRole);
    }

    public boolean disableCoach(String username) {
        return disableRole(username, coachRole);
    } //TODO: make disabling the coach also disable admin

    private boolean enableRole(String username, String role) {
        boolean success = false;
        try {
            if (repository.countByUsernameAndRole(username, role) != 0) {
                System.out.println("The user already has that right.");
            } else {
                UserRole newRole = new UserRole();
                newRole.setUsername(username);
                newRole.setRole(role);

                repository.save(newRole);
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Unable to save rights " + e.getMessage());
        }

        return success;
    }

    private boolean disableRole(String username, String role) {
        boolean success = false;
        try {
            if (repository.countByUsernameAndRole(username, role) == 0) {
                System.out.println("The user does not have that right.");
            } else {
                repository.deleteUserRoleByUsernameAndRole(username, role);
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Unable to remove rights " + e.getMessage());
        }
        return success;
    }
}
