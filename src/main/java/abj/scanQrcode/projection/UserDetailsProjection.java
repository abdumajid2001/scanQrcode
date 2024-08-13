package abj.scanQrcode.projection;

import abj.scanQrcode.enums.UserRole;

public interface UserDetailsProjection {

    Long getId();

    String getUsername();

    String getPassword();

    UserRole getRole();

}
