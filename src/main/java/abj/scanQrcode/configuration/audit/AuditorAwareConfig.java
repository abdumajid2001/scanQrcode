package abj.scanQrcode.configuration.audit;

import abj.scanQrcode.configuration.security.MyUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorAwareConfig implements AuditorAware<Long> {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAwareConfig();
    }

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof MyUserDetails)) {
            return Optional.empty();
        }

        return Optional.of(((MyUserDetails) authentication.getPrincipal()).getId());
    }

}
