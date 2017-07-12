package com.journal.controllers;

import com.journal.entities.UserRole;
import com.journal.services.UserRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JLancaster on 3/15/2017.
 */
@CrossOrigin()
@RestController
public class UserRoleController {

    @Autowired
    private UserRoleManagerService roleManager;

    @RequestMapping(value = "/userRoles/{username}", method = RequestMethod.GET)
    public List<UserRole> getUserRoles(@PathVariable("username") final String username) {
        return roleManager.getRolesByUsername(username);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/enableAdmin", method = RequestMethod.POST)
    public boolean enableAdmin(@RequestBody final String username) {
        return roleManager.enableAdmin(username);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/enableCoach", method = RequestMethod.POST)
    public boolean enableCoach(@RequestBody final String username) {
        return roleManager.enableCoach(username);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/disableAdmin", method = RequestMethod.POST)
    public boolean disableAdmin(@RequestBody final String username) {
        return roleManager.disableAdmin(username);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/userRoles/disableCoach", method = RequestMethod.POST)
    public boolean disableCoach(@RequestBody final String username) {
        return roleManager.disableCoach(username);
    }
}
