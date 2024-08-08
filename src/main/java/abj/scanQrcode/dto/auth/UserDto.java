package abj.scanQrcode.dto.auth;

import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private UserRole role;

    private String firstName;

    private String lastName;

    private String birthDate;

    private Rank rank;

    private String fileUrl;

    private String pictureUrl;

    private String qrCodeText;

    public UserDto(Long id, String username, UserRole role, String firstName, String lastName, String birthDate, Rank rank, String fileUrl, String pictureUrl) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.rank = rank;
        this.fileUrl = fileUrl;
        this.pictureUrl = pictureUrl;
    }

}
