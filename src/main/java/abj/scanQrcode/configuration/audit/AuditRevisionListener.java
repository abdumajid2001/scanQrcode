package abj.scanQrcode.configuration.audit;

import abj.scanQrcode.configuration.security.MyUserDetails;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity auditRevisionEntity = (AuditRevisionEntity) revisionEntity;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof MyUserDetails principal)) {
            auditRevisionEntity.setUsername("anonymous");
            auditRevisionEntity.setUserId(-100L);
            return;
        }
        auditRevisionEntity.setUsername(principal.getUsername());
        auditRevisionEntity.setUserId(principal.getId());
    }

}
