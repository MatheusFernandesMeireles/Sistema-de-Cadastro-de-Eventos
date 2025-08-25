package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String city;
    private List<Event> eventsJoined;

    public User(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.eventsJoined = new ArrayList<>();
    }

    public void joinEvent(Event event) {
        if (!eventsJoined.contains(event)) {
            eventsJoined.add(event);
            event.addParticipant(this);
        }
    }

    public void cancelParticipation(Event event) {
        eventsJoined.remove(event);
        event.removeParticipant(this);
    }

    public List<Event> getEventsJoined() {
        return eventsJoined;
    }

    // Getters e Setters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }
}
