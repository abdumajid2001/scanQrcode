package abj.scanQrcode.dto.user;

import abj.scanQrcode.enums.Position;
import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.Gender;
import abj.scanQrcode.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDto {

    @NotBlank(message = "username must not be blank")
    private String username;

    @NotBlank(message = "password must not be blank")
    private String password;

    @NotEmpty(message = "FirstName may not be empty")
    @Size(min = 6, max = 32, message = "FirstName must be between 6 and 32 characters long")
    private String firstName;

    @NotEmpty(message = "LastName may not be empty")
    @Size(min = 6, max = 32, message = "LastName must be between 6 and 32 characters long")
    private String lastName;

    @NotEmpty(message = "Middle name may not be empty")
    @Size(min = 6, max = 32, message = "Middle name must be between 6 and 32 characters long")
    private String middleName;

    @NotNull(message = "birthDate must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Gender gender;

    @NotEmpty(message = "phoneNumber name may not be empty")
    @Size(min = 6, max = 32, message = "phoneNumber name must be between 6 and 32 characters long")
    private String phoneNumber;

    @NotEmpty(message = "address name may not be empty")
    @Size(min = 6, max = 32, message = "address name must be between 6 and 32 characters long")
    private String address;

    private Position position;

    private Rank rank;

    private UserRole role;

}
