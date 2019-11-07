package com.goruslan.demo.config;

import com.goruslan.demo.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return Optional.of("anonym");
        }
        System.out.println("=================== " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

    }
}
