package com.goruslan.demo.controller;

import com.goruslan.demo.domain.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ScheduleHelper {
    private List<Schedule> scheduleList;

    public ScheduleHelper(){
        scheduleList = new ArrayList<Schedule>();
    }

    public ScheduleHelper(List<Schedule> list){
        scheduleList = list;
    }

    public void addSchedule(Schedule schedule){
        this.scheduleList.add(schedule);
    }

}
