package abj.scanQrcode.configuration.security;

import abj.scanQrcode.entity.Token;
import abj.scanQrcode.repository.TokenRepository;
import abj.scanQrcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenRepository repository;
    private final UserService userService;

    public TokenService(TokenRepository repository,@Lazy UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public synchronized void revokeAllUserTokens(Long userId) {
        String random = UUID.randomUUID().toString();
        List<Token> tokens = repository.findAllValidTokenByUser(userId);
        tokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
            token.setToken(token.getToken() + random);
        });
        repository.saveAll(tokens);
    }

    public void saveUserToken(Long userId, String accessToken) {
        Token token = Token.builder()
                .user(userService.findById(userId))
                .token(accessToken)
                .expired(false)
                .revoked(false)
                .build();
        repository.save(token);
    }

}
