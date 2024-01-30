package com.javameetup.demo.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_group")

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @NonNull
    private String name;

    private String city;
    private String stateOrProvince;
    private String country;
    private String address;
    private String postalCode;

    public Group() {
    }

    public Group(Long id, String name, String city, String stateOrProvince, String country, String address, String postalCode, User user, Set<Event> events) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
        this.address = address;
        this.postalCode = postalCode;
        this.user = user;
        this.events = events;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Event> events;

    public Group(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}