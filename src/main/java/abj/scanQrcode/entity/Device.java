package abj.scanQrcode.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
@Getter
@Setter
public class Device extends Auditable {

    private String deviceModel;

    private String deviceSystem;

    private String macAddress;

    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
