package abj.scanQrcode.service.impl;

import abj.scanQrcode.dto.DeviceCreateDto;
import abj.scanQrcode.dto.DeviceDto;
import abj.scanQrcode.entity.Device;
import abj.scanQrcode.entity.User;
import abj.scanQrcode.repository.DeviceRepository;
import abj.scanQrcode.service.DeviceService;
import abj.scanQrcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DeviceServerImpl implements DeviceService {

    private final UserService userService ;
    private final DeviceRepository deviceRepository ;

    @Override
    public Long createDevice(DeviceCreateDto device) {

        Device newDevice = new Device();

        newDevice.setDeviceModel(device.getDeviceModel());
        newDevice.setDeviceSystem(device.getDeviceSystem());
        newDevice.setMacAddress(device.getMacAddress());
        newDevice.setSerialNumber(device.getSerialNumber());
        newDevice.setUser(userService.findById(device.getUserId()));

        Device save = deviceRepository.save(newDevice);

        return save.getId();
    }

    @Override
    public void deleteById(Long deviceId) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        if (device.isPresent()) {
            Device delete = device.get();

            delete.setDeleted(true);
            deviceRepository.save(delete);
        }
    }

    @Override
    public void deleteByUserId(Long userId) {

        User userOptional = userService.findById(userId);

            List<Device> delete = deviceRepository.getDeviceByUserId(userId);

            for (Device device : delete) {
                if (!device.isDeleted()){
                    device.setDeleted(true);
                    deviceRepository.save(device);
                }
            }
    }

    @Override
    public List<DeviceDto> findByUserId(Long userId) {

        List<DeviceDto> deviceList = new ArrayList<>();

        List<Device> delete = deviceRepository.getDeviceByUserId(userId);

        for (Device device : delete) {

            if (!device.isDeleted()){

                deviceList.add(getDto(device));
            }

        }

        return deviceList;
    }

    private DeviceDto getDto(Device device) {

        DeviceDto deviceDto = new DeviceDto();

        deviceDto.setDeviceModel(device.getDeviceModel());
        deviceDto.setDeviceSystem(device.getDeviceSystem());
        deviceDto.setMacAddress(device.getMacAddress());
        deviceDto.setSerialNumber(device.getSerialNumber());

        return deviceDto ;
    }

}