package com.goruslan.demo.service;

import com.goruslan.demo.domain.Profile;
import com.goruslan.demo.domain.User;
import com.goruslan.demo.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> findAll(){
        return profileRepository.findAllByOrderByIdDesc();
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<Profile> findProfileByUser(User user) { return profileRepository.findProfileByUser(user); }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile register(Profile profile){
        save(profile);
        return profile;
    }

}
