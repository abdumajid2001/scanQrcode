package abj.scanQrcode.dto.user;

import abj.scanQrcode.enums.Position;
import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.Gender;
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

    private String firstName;

    private String lastName;

    private String middleName;

    private String birthDate;

    private Gender gender;

    private String phoneNumber;

    private String address;

    private Position position;

    private Rank rank;

    private UserRole role;

    private String qrCodeText;

    private String fileUrl;

    private String pictureUrl;

}
