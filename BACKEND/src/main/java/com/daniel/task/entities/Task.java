package com.daniel.task.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TASK")
public class Task {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY )
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DONE")
    private Integer done;

    @Column(name = "DATE")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

