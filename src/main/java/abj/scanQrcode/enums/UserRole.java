package abj.scanQrcode.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum UserRole {
    USER,
    ADMIN,
    SUPER_ADMIN
}
