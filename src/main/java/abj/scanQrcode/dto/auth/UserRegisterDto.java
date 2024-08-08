package abj.scanQrcode.dto.auth;

import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserRegisterDto {

    @NotBlank(message = "username must not be blank")
    private String username;

    @NotBlank(message = "password must not be blank")
    private String password;

    @NotBlank(message = "role must not be blank")
    private UserRole role;

    @NotBlank(message = "firstName must not be blank")
    private String firstName;

    @NotBlank(message = "lastName must not be blank")
    private String lastName;

    @NotBlank(message = "birthDate must not be blank")
    private LocalDate birthDate;

    @NotBlank(message = "rank must not be blank")
    private Rank rank;

}
