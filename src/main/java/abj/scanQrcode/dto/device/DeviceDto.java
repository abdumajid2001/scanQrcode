package abj.scanQrcode.dto.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    private Long id;

    private String deviceModel;

    private String deviceSystem;

    private String macAddress;

    private String serialNumber;
}
