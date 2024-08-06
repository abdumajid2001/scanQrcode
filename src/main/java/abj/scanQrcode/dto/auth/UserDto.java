package abj.scanQrcode.dto.auth;

import abj.scanQrcode.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private UserRole role;

    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
