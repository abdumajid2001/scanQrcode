package abj.scanQrcode.utils;

import abj.scanQrcode.enums.Headers;
import abj.scanQrcode.enums.Language;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserSession {

    private final HttpServletRequest request;

    public UserDetails getUser() {
        return (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
    }

    public Language getLanguage() {
        Language language = Language.getValue(request.getHeader(Headers.LANGUAGE.key));

        if (Objects.isNull(language)) {
            language = Language.UZ;
        }

        return language;
    }

    public boolean isAuthentication() {
        try {
            getUser();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
