package abj.scanQrcode.service;

import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.dto.auth.UserDto;
import abj.scanQrcode.entity.User;

import java.util.List;


public interface UserService {

    UserDto getUser();

    Long register(AuthenticationRequest request);

    List<UserDto> getAll();

    void delete(Long id);

    User findById(Long id);

}
