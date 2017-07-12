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
@Table(name="journals")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="journal_id")
    private long journalId;

    @Column(name="journal_name")
    private String journalName;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @CreationTimestamp
    @Column(name="created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name="modified_date")
    private Timestamp modifiedDate;
}
