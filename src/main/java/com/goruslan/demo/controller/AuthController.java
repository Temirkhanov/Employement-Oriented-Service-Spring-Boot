package com.goruslan.demo.controller;

import com.goruslan.demo.domain.Profile;
import com.goruslan.demo.domain.Role;
import com.goruslan.demo.domain.Schedule;
import com.goruslan.demo.domain.User;
import com.goruslan.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Controller
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private UserService userService;
    private ProfileService profileService;
    private RoleService roleService;
    private ScheduleService scheduleService;
    private RequestService requestService;

    public AuthController(UserService userService, ProfileService profileService, RoleService roleService, ScheduleService scheduleService, RequestService requestService) {
        this.userService = userService;
        this.profileService = profileService;
        this.roleService = roleService;
        this.scheduleService = scheduleService;
        this.requestService = requestService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    // ==============================================================================
    // =============================== AGENT REGISTRATION ===========================

    private String[] languages = {"Russian", "Spanish", "French", "Urdu", "Other/None"};

    @GetMapping("/new-agent")
    public String register(Model model) {
        ScheduleHelper scheduleForm = new ScheduleHelper();
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(int i = 0; i < 7; i++){
            scheduleForm.addSchedule(new Schedule());
            scheduleForm.getScheduleList().get(i).setDay(week[i]);
        }
        model.addAttribute("user", new User());
        model.addAttribute("profile", new Profile());
        model.addAttribute("schedule", scheduleForm);
        model.addAttribute("languages", languages);
        return "profile_reg";
    }


    @PostMapping("/new-agent")
    public String registerNewUser(@Valid User user, BindingResult bindingResultUser,
                                  @Valid Profile profile, BindingResult bindingResult,
                                  @ModelAttribute ScheduleHelper schedule,
                                  Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors() || bindingResultUser.hasErrors()) {
            // Show validation errors.
            logger.info("Errors registering a new user.");
            // logger.info(bindingResultSchedule.getAllErrors().toString());
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            model.addAttribute("validationErrorsUser", bindingResultUser.getAllErrors());
            model.addAttribute("user", user);
            model.addAttribute("profile", profile);
            model.addAttribute("schedule", schedule);
            model.addAttribute("languages", languages);
            return "profile_reg";
        } else {
            // Register new user.
            User newUser = userService.register(user);
            profile.setUser(newUser);
            Profile newProfile = profileService.register(profile);
            for(Schedule newSchedule : schedule.getScheduleList()){
                newSchedule.setProfile(newProfile);
                scheduleService.register(newSchedule);
            }
//            for(Schedule newSchedule : schedules.getSchedules()) {
//                newSchedule.setProfile(newProfile);
//                scheduleService.register(newSchedule);
//            }
            redirectAttributes
                    .addFlashAttribute("id", newUser.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/new-agent";
        }
    }

    // ==============================================================================
    // =============================== AGENCY REGISTRATION ===========================

    @GetMapping("/new-agency")
    public String registerAgency(Model model) {
        model.addAttribute("user", new User());
        return "agency_reg";
    }

    @PostMapping("/new-agency")
    public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            // Show validation errors.
            logger.info("Errors registering a new user.");
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "agency_reg";
        } else {
            // Register new user.
            User newUser = userService.register(user);
            redirectAttributes
                    .addFlashAttribute("id", newUser.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/new-agency";
        }
    }


    // ==============================================================================
    // =============================== PROFILE ========================================

    @GetMapping("/profile")
    public String profile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Profile> profile = profileService.findProfileByUser(user);
        if(profile.isPresent()){
            Profile currentProfile = profile.get();
            ScheduleHelper scheduleHelper = new ScheduleHelper(scheduleService.findAllByProfile(currentProfile));
            model.addAttribute("profile", currentProfile);
            model.addAttribute("requests", requestService.getAll(currentProfile.getId()));
            model.addAttribute("schedule", scheduleHelper);
        }
        else {
            // Return error
            logger.info("Couldn't build the profile.");
        }
        return "profile";
    }

    // ==============================================================================
    // =============================== LIST ========================================

    @GetMapping("/list")
    public String listAgents(Model model){
        model.addAttribute("profiles", profileService.findAll());
        Map<Long, List<Schedule>> schedules = scheduleService.findAllProfileSchedules();
        model.addAttribute("schedules", schedules);
        return "list";
    }

    @PostMapping("/list")
    public String findAgents(Model model, List<Schedule> schedules, String gender, String language){
        // Fix here if language property becomes the list
        model.addAttribute(profileService.searchProfiles(schedules, gender, language));
        return "list";
    }
    // ==============================================================================
    // =============================== OTHER ========================================

    @GetMapping("/load")
    public String loadDB(){
        // Password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        // Role
        Role userRole = new Role("ROLE_USER");
        roleService.save(userRole);

        // Users
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            User user = new User("user"+i,secret,true);
            user.addRole(userRole);
            user.setConfirmPassword(secret);
            userService.save(user);
            users.add(user);
        }

        // Profiles
        List<Profile> profiles = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            LocalDate birthDate = LocalDate.of(1992+i,1+i,15+i);
            String gender;
            String language;
            if(i == 1)
                language = "Russian";
            else if(i == 2)
                language = "French";
            else
                language = "Spanish";
            if(i%2 == 0)
                gender = "MALE";
            else
                gender = "FEMALE";
            Profile profile = new Profile("John"+i, "Doe"+i, "347888545"+i, birthDate, gender, "1122"+i, i%2==1, language, users.get(i));
            profileService.save(profile);
            profiles.add(profile);
        }

        // Schedule
        for(int i = 0; i < 5; i++){
            Schedule schedule = new Schedule();
            schedule.setAvail("Monday", true, false, false, profiles.get(i));
            Schedule schedule1 = new Schedule();
            schedule1.setAvail("Tuesday", true, false, true, profiles.get(i));
            Schedule schedule2 = new Schedule();
            schedule2.setAvail("Wednesday", false, false, false, profiles.get(i));
            Schedule schedule3 = new Schedule();
            schedule3.setAvail("Thursday", false, true, true, profiles.get(i));
            Schedule schedule4 = new Schedule();
            schedule4.setAvail("Friday", true, true, true, profiles.get(i));
            Schedule schedule5 = new Schedule();
            schedule5.setAvail("Saturday", false, false, true, profiles.get(i));
            Schedule schedule6 = new Schedule();
            schedule6.setAvail("Sunday", true, false, true, profiles.get(i));

            scheduleService.register(schedule);
            scheduleService.register(schedule1);
            scheduleService.register(schedule2);
            scheduleService.register(schedule3);
            scheduleService.register(schedule4);
            scheduleService.register(schedule5);
            scheduleService.register(schedule6);

        }

        return "index";

    }

//    @GetMapping("/profile")
//    @ResponseBody
//    public Principal user(Principal principal) {
//        return principal;
//    }


}