package com.fbcode.Fb.Booking.Application.entity.flight;


import com.fbcode.Fb.Booking.Application.entity.booking.BookingEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="airport")
public class AirportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 10, unique = true, nullable = false)
    private String code;

    private String name;
    private String city;
    private String state;
    private String country;

    @OneToMany(mappedBy = "originAirport")
    private List<FlightSchedule> originSchedules = new ArrayList<>();

    // Relationship: One Airport → Many Schedules (destination)
    @OneToMany(mappedBy = "destinationAirport")
    private List<FlightSchedule> destinationSchedules = new ArrayList<>();


    public List<FlightSchedule> getOriginSchedules() {
        return originSchedules;
    }

    public void setOriginSchedules(List<FlightSchedule> originSchedules) {
        this.originSchedules = originSchedules;
    }

    public List<FlightSchedule> getDestinationSchedules() {
        return destinationSchedules;
    }

    public void setDestinationSchedules(List<FlightSchedule> destinationSchedules) {
        this.destinationSchedules = destinationSchedules;
    }



    public AirportEntity() {
    }

    public AirportEntity(Long id, String code, String name, String city, String state, String country) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
