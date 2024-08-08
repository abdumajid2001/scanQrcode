package abj.scanQrcode.service;

import abj.scanQrcode.dto.DeviceCreateDto;
import abj.scanQrcode.dto.DeviceDto;

import java.util.List;

public interface DeviceService {

    Long createDevice(DeviceCreateDto device);

    void deleteById(Long deviceId);

    void deleteByUserId(Long userId);

    List<DeviceDto> findByUserId(Long userId);

}
