package com.cv.job.config;

import com.cv.job.models.ApplicationUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Long> {
    @SuppressWarnings("NullableProblems")
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
        !authentication.isAuthenticated() ||
        authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        ApplicationUser userPrincipal = (ApplicationUser) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }
}
