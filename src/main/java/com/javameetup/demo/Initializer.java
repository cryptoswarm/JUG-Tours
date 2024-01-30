package com.javameetup.demo;


import com.javameetup.demo.models.Event;
import com.javameetup.demo.models.Group;
import com.javameetup.demo.repository.IGroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    private final IGroupRepository groupRepository;

    public Initializer(IGroupRepository iGroupRepository) {
        this.groupRepository = iGroupRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG",
                "London JUG").forEach(name -> groupRepository.save(new Group(name))
        );

        Group group = groupRepository.findByName("Seattle JUG");

        Event event = new Event.EventBuilder()
                                .withTitle("Micro Frontends for Java Developers")
                                .withDescription("JHipster now has microfrontend support!")
                                .withDate(Instant.parse("2022-11-14T17:00:00.000Z"))
                                .Build();

        group.setEvents(Collections.singleton(event));

        groupRepository.save(group);
        //for debugging purpose
        groupRepository.findAll().forEach(System.out::println);
    }
}
