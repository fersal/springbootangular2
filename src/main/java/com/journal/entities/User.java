package com.journal.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by jonathon lancaster on 1/21/2017.
 */
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private long userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String username;

    private String password;

    private boolean enabled;

    @CreationTimestamp
    @Column(name="created_date")
    private Date createdDate;

    @Column(name="modified_date", updatable = false, insertable = false)
    private Timestamp modifiedDate;

}
