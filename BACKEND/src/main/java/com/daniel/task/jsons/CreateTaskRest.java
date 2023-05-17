package com.daniel.task.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTaskRest {

    @JsonProperty("description")
    private String description;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("done")
    private Integer done;

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}