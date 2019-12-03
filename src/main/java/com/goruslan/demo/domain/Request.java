package com.goruslan.demo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Request {

    @Id
    @GeneratedValue
    private Long id;

    private Long fromUserId;

    private Long profileId;
}
