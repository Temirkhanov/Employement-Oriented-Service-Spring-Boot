package com.goruslan.demo.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@NoArgsConstructor
@Setter
@ToString
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty(message = "Day of the availability is required.")
    private String day;

    @NonNull
    @Column(nullable = false)
    private boolean morning;

    @NonNull
    @Column(nullable = false)
    private boolean afternoon;

    @NonNull
    @Column(nullable = false)
    private boolean evening;

    @ManyToOne      // Relationship. Many days belong to one user.
    private Profile profile;

    public long getProfileId(){
        return profile.getId();
    }

    public void setAvail(String d, boolean m, boolean a, boolean e, Profile p){
        day = d;
        morning = m;
        evening = e;
        afternoon = a;
        profile = p;

    }
}
