package abj.scanQrcode.repository;

import abj.scanQrcode.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("select t  from Device t inner join User u on t.user = u where u.id = :id ")
    List<Device> getDeviceByUserId(Long userId);
}
