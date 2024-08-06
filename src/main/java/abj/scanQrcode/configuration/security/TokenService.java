package abj.scanQrcode.configuration.security;

import abj.scanQrcode.entity.Token;
import abj.scanQrcode.entity.User;
import abj.scanQrcode.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public synchronized void revokeAllUserTokens(User user) {
        String random = UUID.randomUUID().toString();
        List<Token> tokens = tokenRepository.findAllValidTokenByUser(user.getId());
        tokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
            token.setToken(token.getToken() + random);
        });
        tokenRepository.saveAll(tokens);
    }

    public void saveUserToken(User user, String accessToken) {
        Token token = Token.builder()
                .user(user)
                .token(accessToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
