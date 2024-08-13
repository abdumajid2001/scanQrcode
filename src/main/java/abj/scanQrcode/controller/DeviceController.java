package abj.scanQrcode.controller;

import abj.scanQrcode.dto.DeviceCreateDto;
import abj.scanQrcode.dto.DeviceDto;
import abj.scanQrcode.dto.responce.DataDto;
import abj.scanQrcode.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device/")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService service;

    @PostMapping("create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody DeviceCreateDto device) {
        return new ResponseEntity<>(
                new DataDto<>(service.createDevice(device)) ,
                HttpStatus.OK
        );
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @DeleteMapping("deleteByUserId/{id}")
    public void deleteByUserId(@PathVariable("id") Long id) {
        service.deleteByUserId(id);
    }

    @GetMapping("getAllByUserId/{id}")
    public ResponseEntity<DataDto<List<DeviceDto>>> getAllByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new DataDto<>(service.findByUserId(id)),
                HttpStatus.OK
        );
    }

}
