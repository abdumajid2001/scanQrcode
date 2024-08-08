package abj.scanQrcode.service.impl;

import abj.scanQrcode.dto.auth.UserDto;
import abj.scanQrcode.dto.auth.UserRegisterDto;
import abj.scanQrcode.entity.User;
import abj.scanQrcode.exception.AlreadyException;
import abj.scanQrcode.exception.NotFoundException;
import abj.scanQrcode.repository.UserRepository;
import abj.scanQrcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${baseUrl}")
    private String baseUrl;

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public UserDto getByQrcode(String qrCodeText) {
        return toDto(repository.findByQrCodeText(qrCodeText));
    }

    @Override
    public UserDto getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return toDto(user);
    }

    @Override
    public Long register(UserRegisterDto dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new AlreadyException(dto.getUsername() + "- username mavjud !!!");
        }
        String qrText = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        User user = new User(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getRole(), dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getRank(), qrText);

        User savedUser = repository.save(user);

        return savedUser.getId();
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAllBy()
                .parallelStream()
                .map(user -> {
                    UserDto dto = toDto(user);
                    dto.setQrCodeText(user.getQrCodeText());
                    return dto;
                })
                .toList();
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

    public User save(User user) {
        return repository.save(user);
    }

    public UserDto toDto(User user) {
        String baseUrl = this.baseUrl + "/file/download/";
        String fileUrl = Objects.nonNull(user.getFile()) ? baseUrl + "file/" + user.getFile().getId() : "";
        String pictureUrl = Objects.nonNull(user.getPicture()) ? baseUrl + "picture/" + user.getPicture().getId() : "";

        return new UserDto(user.getId(), user.getUsername(), user.getRole(), user.getFirstName(), user.getLastName(), user.getBirthDate().toString(), user.getRank(), fileUrl, pictureUrl);
    }

}
