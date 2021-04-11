package de.dta.feed.greeting.service;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GreetingService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminGreeting() {
        return "Hello, Admin!";
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public String superAdminGreeting() {
        return "Secret greeting";
    }

    @PostFilter(
            "filterObject.contains(authentication.name)"
    )
    public List<String> greetings() {
        return Stream.of(
                "Hello, Reader.",
                "Guten Tag, leser."
        ).collect(Collectors.toList());
    }
}
