package abj.scanQrcode.service;

import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.dto.auth.UserDto;

import java.util.List;


public interface UserService {

    UserDto getUser();

    Long register(AuthenticationRequest request);

    List<UserDto> getAll();

    void delete(Long id);

}
