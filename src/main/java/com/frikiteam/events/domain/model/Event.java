package com.frikiteam.events.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logo;
    private String information;
    private String name;
    private Double price;
    private int quantity;
    private Boolean verified;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;

    // relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Place place;

    @OneToMany(mappedBy = "event")
    private List<EventInformation> eventInformations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "events_tags",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "social_networks_Events",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "social_network_id")})
    private List<SocialNetwork> socialNetworks = new ArrayList<>();

    // inverse relationships
    @OneToMany(mappedBy = "event")
    List<EventQualification> eventQualifications = new ArrayList<>();


    public Event(String name, int quantity, Double price, String information, Boolean verified, GregorianCalendar startDate, GregorianCalendar endDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.information = information;
        this.verified = verified;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}