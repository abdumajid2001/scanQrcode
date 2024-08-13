package abj.scanQrcode.repository;

import abj.scanQrcode.dto.device.DeviceDto;
import abj.scanQrcode.entity.Device;
import abj.scanQrcode.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("select new abj.scanQrcode.dto.device.DeviceDto(t.id,t.deviceModel,t.deviceSystem , t.macAddress , t.serialNumber)  from Device t inner join User u on t.user = u where u.id = :userId and t.deleted = false ")
    List<DeviceDto> getDeviceByUserId(Long userId);

    List<Device> findByUserAndDeletedFalse(User user);
}
