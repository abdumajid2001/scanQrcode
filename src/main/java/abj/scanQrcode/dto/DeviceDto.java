package abj.scanQrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    private String deviceModel;

    private String deviceSystem;

    private String macAddress;

    private String serialNumber;
}
