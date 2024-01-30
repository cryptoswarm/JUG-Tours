package com.javameetup.demo.models;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Instant date;
    private String title;
    private String description;

    @ManyToMany
    private Set<User> attendees;

    public Event() {
    }

    public Event(EventBuilder builder) {
        this.attendees = builder.attendees;
        this.date = builder.date;
        this.description = builder.description;
        this.title = builder.title;
    }

    public Event(Instant date, String title, String description, Set<User> attendees) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.attendees = attendees;
    }

    public static class EventBuilder{
        private Long id;
        private Instant date;
        private String title;
        private String description;
        private Set<User> attendees;

        public EventBuilder withTitle(String title){
            this.title = title;
            return  this;
        }

        public EventBuilder withDescription(String description){
            this.description = description;
            return  this;
        }

        public EventBuilder withDate(Instant date){
            this.date = date;
            return  this;
        }

        public Event Build(){
            var event = new Event(this);
            return  event;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees;
    }
}
