package abj.scanQrcode.service;

import abj.scanQrcode.dto.DeviceCreateDto;
import abj.scanQrcode.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
//    create
    Long createDevice(DeviceCreateDto device);
//deleteById
    void deleteById(Long deviceId);
//deleteByUserId( userId )
    void deleteByUserId(Long userId);
//getAllByUserId( userId )
    List<DeviceDto> findByUserId(Long userId);


}
