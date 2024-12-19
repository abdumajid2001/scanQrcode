package abj.scanQrcode.controller;

import abj.scanQrcode.dto.responce.DataDto;
import abj.scanQrcode.dto.user.UserDto;
import abj.scanQrcode.dto.user.UserRegisterDto;
import abj.scanQrcode.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController handles all user-related operations such as registration,
 * retrieving user information, and managing user accounts.
 */
@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@Tag(name = "User", description = "API for managing users through QR code text, registration, and more.")
public class UserController {

    private final UserService service;

    @GetMapping("getByQrCodeText")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','USER')")
    public ResponseEntity<DataDto<UserDto>> getByQrCodeText(
            @Parameter(description = "The text encoded in the user's QR code", required = true)
            @RequestParam String qrCodeText) {
        return ResponseEntity.ok(new DataDto<>(service.getByQrcode(qrCodeText)));
    }

    @GetMapping("get")
    public ResponseEntity<DataDto<UserDto>> getUser() {
        return new ResponseEntity<>(
                new DataDto<>(service.getUser()),
                HttpStatus.OK
        );
    }

    @PostMapping("register")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<DataDto<Long>> register(
            @Valid @RequestBody UserRegisterDto registerDto) {
        return new ResponseEntity<>(
                new DataDto<>(service.register(registerDto)),
                HttpStatus.OK
        );
    }

    @GetMapping("getAll")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<DataDto<List<UserDto>>> getALL() {
        return ResponseEntity.ok(new DataDto<>(service.getAll()));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public void delete(
            @Parameter(description = "The ID of the user to delete", required = true)
            @PathVariable("id") Long id) {
        service.delete(id);
    }

}
