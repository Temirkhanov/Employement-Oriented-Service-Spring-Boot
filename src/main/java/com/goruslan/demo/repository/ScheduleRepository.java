package com.goruslan.demo.repository;

import com.goruslan.demo.domain.Profile;
import com.goruslan.demo.domain.Schedule;
import com.goruslan.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findFirstByProfile(Profile profile);

    List<Schedule> findAllByProfile(Profile profile);

    List<Schedule> findAll();

    List<Schedule> findAllByDay(String day);

}