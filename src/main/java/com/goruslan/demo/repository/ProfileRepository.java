package com.goruslan.demo.repository;

import com.goruslan.demo.domain.Profile;
import com.goruslan.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByOrderByIdDesc();

    Optional<Profile> findProfileByUser(User user);


    //@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")


}
