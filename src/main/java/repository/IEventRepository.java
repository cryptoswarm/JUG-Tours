package repository;

import models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);
}
