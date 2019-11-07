package com.goruslan.demo.service;

import com.goruslan.demo.domain.Profile;
import com.goruslan.demo.domain.Schedule;
import com.goruslan.demo.domain.User;
import com.goruslan.demo.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    private Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() { return scheduleRepository.findAll(); }

    public Map<Long, List<Schedule>> findAllProfileSchedules() {
        Map<Long, List<Schedule>> map = new HashMap<>();
        for(Schedule schedule : scheduleRepository.findAll()){
            Long id = schedule.getProfileId();
            if(!map.containsKey(id)){
                List<Schedule> list = new ArrayList<>();
                list.add(schedule);
                map.put(id, list);
            }
            else {
                map.get(id).add(schedule);
            }
        }
        return map;
    }

    public Schedule register(Schedule schedule){
        save(schedule);
        return schedule;
    }

    public List<Schedule> findAllByProfile(Profile profile) { return scheduleRepository.findAllByProfile(profile); }
}
