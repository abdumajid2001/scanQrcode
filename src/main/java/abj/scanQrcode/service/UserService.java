package abj.scanQrcode.service;

import abj.scanQrcode.dto.user.UserRegisterDto;
import abj.scanQrcode.dto.user.UserDto;
import abj.scanQrcode.entity.User;

import java.util.List;


public interface UserService {

    UserDto getByQrcode(String qrcode);

    UserDto getUser();

    Long register(UserRegisterDto registerDto);

    List<UserDto> getAll();

    void delete(Long id);

    User findById(Long id);

    User save(User user);

}
