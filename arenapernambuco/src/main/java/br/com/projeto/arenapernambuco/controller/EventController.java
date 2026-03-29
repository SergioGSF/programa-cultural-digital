package br.com.projeto.arenapernambuco.controller;

import br.com.projeto.arenapernambuco.model.Event;
import br.com.projeto.arenapernambuco.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public String listEvents(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String q,
            Model model
    ) {
        List<Event> events;

        if (category != null && !category.isBlank()) {
            events = eventRepository.findByCategory_NameIgnoreCase(category);
        } else if (q != null && !q.isBlank()) {
            events = eventRepository.findByTitleContainingIgnoreCase(q);
        } else {
            events = eventRepository.findAll();
        }

        model.addAttribute("events", events);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("q", q);

        return "events"; // templates/events.html
    }
}
