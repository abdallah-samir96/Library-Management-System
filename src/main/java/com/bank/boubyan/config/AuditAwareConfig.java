package com.bank.boubyan.config;

import com.bank.boubyan.service.security.JWTService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
@EnableJpaAuditing
public class AuditAwareConfig implements AuditorAware<String> {

    private final JWTService jwtService;
    public AuditAwareConfig(JWTService jwtService) {
        this.jwtService = jwtService;
    }
    @Override
    public Optional<String> getCurrentAuditor() {
        var contextHolder = RequestContextHolder.getRequestAttributes();
        if(contextHolder == null) {
            return Optional.empty();
        }
        var request = ((ServletRequestAttributes)contextHolder).getRequest();
        var accessToken = request.getHeader("Authorization");
        if(accessToken == null || accessToken.isBlank()) {
            return Optional.empty();
        }
        accessToken = accessToken.replace("Bearer ","");
        return Optional.of(jwtService.getUserEmail(accessToken));
    }
}
