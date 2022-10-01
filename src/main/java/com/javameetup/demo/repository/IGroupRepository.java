package com.javameetup.demo.repository;

import com.javameetup.demo.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);

    List<Group> findAllByUserId(String id);
}
