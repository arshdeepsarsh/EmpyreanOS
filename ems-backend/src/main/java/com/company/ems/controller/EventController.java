package com.company.ems.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.company.ems.model.Event;
import com.company.ems.repository.EventRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Send all events to the calendar
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Save a new event to the database
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }
}