package com.journal.services;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

/**
 * Created by jonathon lancaster on 4/5/2017.
 */
@Service
public class JournalEntryManagerService {
    @Resource
    private JournalEntryRepository repository;
    @Resource
    private JournalManagerService journalManager;

    public JournalEntry loadTodaysEntry() {
        JournalEntry entry = new JournalEntry();
        try {
            Journal journal = journalManager.getJournalForLoggedInUser();
            Long time = System.currentTimeMillis();
            Date today = new Date(time);
            if (journal != null) {
                entry = repository.findByJournalIdAndCreatedDate(journal.getJournalId(), today);
            }
        } catch (Exception e) {
            System.out.println("There was an error loading today's journal entry. " + e.getMessage());
        }

        return entry;
    }

    public JournalEntry saveEntry(JournalEntry entry) {
        Journal journal = journalManager.getJournalForLoggedInUser();
        JournalEntry todaysEntry = loadTodaysEntry();

        try {
            entry.setJournalId(journal.getJournalId());
            if (todaysEntry != null) {
                entry.setJournalEntryId(todaysEntry.getJournalEntryId());
                entry.setCreatedDate(todaysEntry.getCreatedDate());
            }
            todaysEntry = repository.save(entry);
        } catch (Exception e) {
            System.out.println("There was an error saving the journal entry " + e.getMessage());
            return null;
        }

        return todaysEntry;
    }
}
