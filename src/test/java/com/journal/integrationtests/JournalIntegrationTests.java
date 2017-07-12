package com.journal.integrationtests;

import com.journal.entities.Journal;
import com.journal.entities.JournalEntry;
import com.journal.repositories.JournalEntryRepository;
import com.journal.repositories.JournalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JournalIntegrationTests {

	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private JournalEntryRepository journalEntryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testFindJournalByJournalId() {
		Journal journal = journalRepository.findByJournalId(1);
		assertEquals("my journal", journal.getJournalName());
	}

//	@Test
//	public void testFindAllJournalsByUserId() {
//		Journal journals = journalRepository.findByUserUserId(1);
//		assertEquals(2, journals.size());
//	}
//
//	@Test
//	public void testFindJournalsByUsername() {
//		List<Journal> journals = journalRepository.findByUserUsername("jonlan");
//		assertEquals(2, journals.size());
//	}

	@Test
	public void testFindJournalEntryByJournalEntryId() {
		JournalEntry journalEntry = journalEntryRepository.findByJournalEntryId(1);
		assertEquals(1, journalEntry.getJournalEntryId());
	}

	@Test
	public void testFindAllJournalEntriesByJournalId() {
		List<JournalEntry> entryList = journalEntryRepository.findByJournalId(1);
		assertEquals(2, entryList.size());
	}
}
