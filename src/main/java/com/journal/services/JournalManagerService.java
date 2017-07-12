package com.journal.services;

import com.journal.entities.Journal;
import com.journal.entities.User;
import com.journal.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jonathon lancaster on 2/25/2017.
 */
@Service
public class JournalManagerService {
    @Resource
    private SecurityService security;
    @Autowired
    private JournalRepository repository;

    public Journal createJournal(User user) {
        Journal newJournal = new Journal();
        newJournal.setJournalName(user.getFirstName() + "\'s Journal");
        newJournal.setUser(user);
        try {
            newJournal = repository.save(newJournal);
        } catch (Exception exception) {
            System.out.println("There was an exception saving the new user. " + exception.getMessage());
        }
        return newJournal;
    }

    public Journal getJournalForLoggedInUser() {
        Journal journal = new Journal();
        String username = security.getLoggedInUser();

        try {
            journal = repository.findByUserUsername(username);
        } catch (Exception e) {
            System.out.println("There was an error finding the logged in user's journal." + e.getMessage());
        }

        return journal;
    }

}
