package com.goruslan.demo.service;

import com.goruslan.demo.domain.Profile;
import com.goruslan.demo.domain.Schedule;
import com.goruslan.demo.domain.User;
import com.goruslan.demo.repository.ProfileRepository;
import com.goruslan.demo.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ScheduleRepository scheduleRepository;

    public ProfileService(ProfileRepository profileRepository, ScheduleRepository scheduleRepository) {
        this.profileRepository = profileRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Profile> findAll(){
        return profileRepository.findAllByOrderByIdDesc();
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<Profile> findProfileByUser(User user) { return profileRepository.findProfileByUser(user); }

    public Set<Profile> searchProfiles(List<Schedule> schedules, String gender, String language){
        Set<Profile> scheduleMatch = new HashSet<>();
        // Handle exceptions and corner cases here
        // ...

        for(Schedule s : schedules) {
            for(Schedule sch : scheduleRepository.findAllByDay(s.getDay())) {
                if(s.isMorning() && !sch.isMorning()){
                    break;
                }
                if(s.isAfternoon() && !sch.isAfternoon()){
                    break;
                }
                if(s.isEvening() && !sch.isEvening()) {
                    break;
                }
                Profile profile = sch.getProfile();

                // Fix the if statement after changing the language to the list
                if(!gender.equals("both") && gender.equals(profile.getGender())
                    && profile.getLanguage().equals(language)) {
                    scheduleMatch.add(sch.getProfile());
                }
            }
        }

        return scheduleMatch;
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile register(Profile profile){
        save(profile);
        return profile;
    }

}
