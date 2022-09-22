package com.javameetup.demo.repository;

import com.javameetup.demo.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
