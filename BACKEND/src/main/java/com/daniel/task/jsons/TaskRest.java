package com.daniel.task.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRest {

    @JsonProperty("id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @JsonProperty("description")
    private String description;

    @JsonProperty("done")
    private Integer done;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}