package com.company.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.ems.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}