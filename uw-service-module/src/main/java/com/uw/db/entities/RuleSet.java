package com.uw.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "RULESET")
public class RuleSet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private int version;
    private Date createdDate;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
