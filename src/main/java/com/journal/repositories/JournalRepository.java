package com.journal.repositories;

import com.journal.entities.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jonathon lancaster on 1/31/2017.
 */
@Repository
public interface JournalRepository extends JpaRepository <Journal, Integer> {
    Journal findByJournalId (int journalId);

    Journal findByUserUserId(int userId);

    Journal findByUserUsername(String username);

    Journal save (Journal journal);

    @Modifying
    @Transactional
    void delete(Journal journal);
}

