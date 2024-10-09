package abj.scanQrcode.controller;

import abj.scanQrcode.dto.user.UserRegisterDto;
import abj.scanQrcode.dto.user.UserDto;
import abj.scanQrcode.dto.responce.DataDto;
import abj.scanQrcode.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Get a user by QR code text",
            description = "Retrieves a user based on the provided QR code text. " +
                    "Roles required: USER, ADMIN, SUPER_ADMIN.",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user data")
    })
    @GetMapping("getByQrCodeText")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','USER')")
    public ResponseEntity<DataDto<UserDto>> getByQrCodeText(
            @Parameter(description = "The text encoded in the user's QR code", required = true)
            @RequestParam String qrCodeText) {
        return ResponseEntity.ok(new DataDto<>(service.getByQrcode(qrCodeText)));
    }

    @Operation(summary = "Get the current user",
            description = "Retrieves information about the currently authenticated user.",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of current user data")
    })
    @GetMapping("get")
    public ResponseEntity<DataDto<UserDto>> getUser() {
        return new ResponseEntity<>(
                new DataDto<>(service.getUser()),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Register a new user",
            description = "Registers a new user in the system with the provided details. " +
                    "Roles required: ADMIN, SUPER_ADMIN.",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration with user ID")
    })
    @PostMapping("register")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<DataDto<Long>> register(
            @Valid @RequestBody UserRegisterDto registerDto) {
        return new ResponseEntity<>(
                new DataDto<>(service.register(registerDto)),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Get all users",
            description = "Retrieves a list of all registered users. " +
                    "Roles required: ADMIN, SUPER_ADMIN.",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of all users")
    })
    @GetMapping("getAll")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<DataDto<List<UserDto>>> getALL() {
        return ResponseEntity.ok(new DataDto<>(service.getAll()));
    }

    @Operation(summary = "Delete a user by ID",
            description = "Deletes the user identified by the provided ID. " +
                    "Roles required: ADMIN, SUPER_ADMIN.",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content, successful deletion")
    })
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public void delete(
            @Parameter(description = "The ID of the user to delete", required = true)
            @PathVariable("id") Long id) {
        service.delete(id);
    }

}
