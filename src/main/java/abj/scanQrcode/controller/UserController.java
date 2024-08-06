package abj.scanQrcode.controller;

import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.dto.auth.UserDto;
import abj.scanQrcode.dto.responce.DataDto;
import abj.scanQrcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("get")
    public ResponseEntity<DataDto<UserDto>> getUser() {
        return new ResponseEntity<>(
                new DataDto<>(service.getUser()),
                HttpStatus.OK
        );
    }

    @PostMapping("register")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','USER')")
    public ResponseEntity<DataDto<Long>> register(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(
                new DataDto<>(service.register(request)),
                HttpStatus.OK
        );
    }

    @GetMapping("getAll")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','USER')")
    public ResponseEntity<DataDto<List<UserDto>>> getALL() {
        return ResponseEntity.ok(new DataDto<>(service.getAll()));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
