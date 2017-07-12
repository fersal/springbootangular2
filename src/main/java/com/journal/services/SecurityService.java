package com.journal.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by jonathon lancaster on 4/5/2017.
 */
@Service
public class SecurityService {

    public String getLoggedInUser() {
        String currentPrincipalName = "";

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                currentPrincipalName = authentication.getName();
            }
        } catch (Exception e) {
            System.out.println("There was an error getting the logged in user. " + e.getMessage());
        }
        return currentPrincipalName;
    }
}
