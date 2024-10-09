package abj.scanQrcode.projection;

import abj.scanQrcode.enums.Position;
import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.Gender;
import abj.scanQrcode.enums.UserRole;

public interface UserDtoProjection {

    Long getId();

    String getUsername();

    String getFirstname();

    String getLastname();

    String getMiddleName();

    String getBirthdate();

    Gender getGender();

    String getPhoneNumber();

    String getAddress();

    Position getPosition();

    Rank getRank();

    UserRole getRole();

    String getFileId();

    String getPictureId();

    String getQrCodeText();

}
