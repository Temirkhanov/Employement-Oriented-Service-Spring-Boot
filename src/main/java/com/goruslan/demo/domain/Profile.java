package com.goruslan.demo.domain;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Profile extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotEmpty(message = "First name is required.")
    private String first;

    @NonNull
    @NotEmpty(message = "Last name is required.")
    private String last;

    @NonNull
    @NotEmpty(message = "Phone number is required.")
    @Column(unique = true, length = 10)
    private String phone;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NonNull
    @NotEmpty(message = "Gender is required.")
    private String gender;

    @NonNull
    @NotEmpty(message = "Zip code is required.")
    private String zip;

    @NonNull
    private boolean english;

    @NonNull
    @NotEmpty(message = "Second language input is required.")
    private String language;

    private String address;

    private String ethnicity;

    @OneToOne
    @MapsId
    private User user;

    public long getUserId(){
        return user.getId();
    }

    public Profile(String first, String last, String phone, LocalDate dob, String gender, String zip, boolean english, String language, User user) {
        this.first = first;
        this.last = last;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.zip = zip;
        this.english = english;
        this.language = language;
        this.user = user;
    }

    public boolean getEnglish(){
        return english;
    }

    //    public void addLanguage(int lang){
//        switch(lang) {
//            case 1:
//                languages.add("English");
//                break;
//            case 2:
//                languages.add("Spanish");
//                break;
//            default:
//                languages.add("Russian");
//        }
//    }


}
