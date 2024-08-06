package abj.scanQrcode.configuration.security;

import abj.scanQrcode.entity.User;
import abj.scanQrcode.exception.UsernameNotFoundException;
import abj.scanQrcode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.debug("Entering in loadUserByUsername Method...");
        Optional<User> optionalUserInfo = repository.findByUsername(username);

        if (optionalUserInfo.isEmpty()) {
            logger.error("Username not found: {}", username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return optionalUserInfo.get();
    }

}
