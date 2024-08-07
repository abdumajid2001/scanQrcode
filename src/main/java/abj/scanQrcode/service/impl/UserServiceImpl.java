package abj.scanQrcode.service.impl;

import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.dto.auth.UserDto;
import abj.scanQrcode.entity.User;
import abj.scanQrcode.enums.UserRole;
import abj.scanQrcode.exception.AlreadyException;
import abj.scanQrcode.exception.NotFoundException;
import abj.scanQrcode.exception.NotNullFieldException;
import abj.scanQrcode.repository.UserRepository;
import abj.scanQrcode.service.UserService;
import abj.scanQrcode.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public UserDto getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new UserDto(user.getId(), user.getUsername(), user.getRole());
    }

    @Override
    public Long register(AuthenticationRequest request) {
        //todo annotation base qil
        if (Util.isNullFields(request.getUsername(), request.getPassword())) {
            throw new NotNullFieldException("Field not null");
        }

        if (repository.existsByUsername(request.getUsername())) {
            throw new AlreadyException(request.getUsername() + "- username mavjud !!!");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(UserRole.ADMIN)
                .build();
        User savedUser = repository.save(user);

        return savedUser.getId();
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setUsername(user.getUsername() + UUID.randomUUID());
            user.setDeleted(true);
            repository.save(user);
        }
    }

    @Override
    public User findById(Long id) {
        return repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }


}
