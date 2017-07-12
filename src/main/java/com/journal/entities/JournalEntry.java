package com.journal.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
@Table(name="journal_entries")
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="journal_entry_id")
    private long journalEntryId;

    @Column(name="journal_id")
    private long journalId;

    @Column(name="positive_review")
    private String positiveReview;

    private String goal;

    private String momentum;

    @Column(name="mental_toughness")
    private int mentalToughness;

    private int willingness;

    private int determination;

    private int motivation;

    private int attitude;

    @Column(name="personal_impact")
    private int personalImpact;

    @CreationTimestamp
    @Column(name="created_date")
    private Date createdDate;

    @Column(name="modified_date", insertable = false, updatable = false)
    private Timestamp modifiedDate;
}
