package com.goruslan.demo.repository;

import com.goruslan.demo.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByProfileId(Long profileId);

}
