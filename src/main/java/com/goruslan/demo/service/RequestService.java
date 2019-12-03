package com.goruslan.demo.service;

import com.goruslan.demo.domain.Request;
import com.goruslan.demo.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }

    public List<Request> getAll(Long profileId) {
        return requestRepository.findAllByProfileId(profileId);
    }
}
