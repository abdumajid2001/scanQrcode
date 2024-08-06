package abj.scanQrcode.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

    private String username;

    private String password;

    public AuthenticationRequest(String username) {
        this.username = username;
    }
}
