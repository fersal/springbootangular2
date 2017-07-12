package com.journal.controllers;

import com.journal.entities.Journal;
import com.journal.entities.User;
import com.journal.entities.UserRole;
import com.journal.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JLancaster on 3/13/2017.
 */
@CrossOrigin()
@RestController
public class UserController {

    @Autowired
    private UserManagerService userManager;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody final User newUser) {

        return userManager.createNewUser(newUser);
    }

//    @PreAuthorize("hasRole('ROLE_COACH')")
//    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
//    public User findUser(@RequestParam("username") String username) {
//        return userManager.findUser(username);
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/disable", method = RequestMethod.POST)
    public boolean disableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.disableUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/enable", method = RequestMethod.POST)
    public boolean enableUser(@RequestParam("username") String username) {
        User user = userManager.findUser(username);
        return userManager.enableUser(user);
    }

    @RequestMapping(value = "/users/authenticate", method = RequestMethod.POST)
    public Journal authenticate(@RequestBody final User user) {
        User authenticatedUser = new User();
        authenticatedUser.setEnabled(true);
        authenticatedUser.setUserId(2);
        authenticatedUser.setFirstName("Fernando");
        authenticatedUser.setLastName("Salazar");
        authenticatedUser.setUsername(user.getUsername());
        authenticatedUser.setPassword(user.getPassword());

        Journal userJournal = new Journal();
        userJournal.setUser(authenticatedUser);
        userJournal.setJournalId(2);
        userJournal.setJournalName("Fernando's Journal");
        return userJournal;
    }

    @RequestMapping(value = "/users/pwd", method = RequestMethod.POST)
    public User setPassword(@RequestBody final User user) {
        User userWithNewPassword = new User();
        userWithNewPassword.setPassword(user.getPassword());
        userWithNewPassword.setUserId(user.getUserId());
        userWithNewPassword.setFirstName("Fernando");
        return userWithNewPassword;
    }

    @RequestMapping(value = "/users/name", method = RequestMethod.POST)
    public User setUserName(@RequestBody final User user) {
        User userWithNewName = new User();
        userWithNewName.setFirstName(user.getFirstName());
        userWithNewName.setLastName(user.getLastName());
        userWithNewName.setUserId(user.getUserId());
        return userWithNewName;
    }

    @RequestMapping(value = "/users/find", method = RequestMethod.POST)
    public User findUser(@RequestBody final User user) {
        User userFound = new User();
        userFound.setUsername(user.getUsername());
        userFound.setFirstName("James");
        userFound.setLastName("Bond");
        userFound.setUserId(4);
        return userFound;
    }

    @RequestMapping(value = "/users/updateRole", method = RequestMethod.POST)
    public UserRole updateRole(@RequestBody final UserRole userRole) {
        UserRole savedRole = new UserRole();
        savedRole.setUserRoleId(456);
        savedRole.setUsername(userRole.getUsername());
        savedRole.setRole("ROLE_ADMIN");
        return savedRole;
    }

    @RequestMapping(value = "/users/getRole", method = RequestMethod.POST)
    public UserRole getRole(@RequestBody final User user) {
        UserRole role = new UserRole();
        role.setRole("ROLE_COACH");
        role.setUsername(user.getUsername());
        role.setUserRoleId(6666);
        return role;
    }
}
