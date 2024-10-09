package abj.scanQrcode.service.impl;

import abj.scanQrcode.configuration.security.MyUserDetails;
import abj.scanQrcode.dto.user.UserDto;
import abj.scanQrcode.dto.user.UserRegisterDto;
import abj.scanQrcode.entity.User;
import abj.scanQrcode.exception.AlreadyException;
import abj.scanQrcode.exception.NotFoundException;
import abj.scanQrcode.projection.UserDtoProjection;
import abj.scanQrcode.repository.UserRepository;
import abj.scanQrcode.service.FileStorageService;
import abj.scanQrcode.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${baseUrl}")
    private String baseUrl;

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final FileStorageService fileStorageService;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder, @Lazy FileStorageService fileStorageService) {
        this.repository = repository;
        this.encoder = encoder;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public UserDto getByQrcode(String qrCodeText) {
        UserDtoProjection projection = repository
                .findByQrCodeText(qrCodeText)
                .orElseThrow(() -> new NotFoundException(qrCodeText + " not found"));

        return toDto(projection);
    }

    @Override
    public UserDto getUser() {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        UserDtoProjection projection = repository
                .findUserById(user.getId())
                .orElseThrow(() -> new NotFoundException(user.getId() + " not found"));

        return toDto(projection);
    }

    @Override
    public Long register(UserRegisterDto dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new AlreadyException(dto.getUsername() + "- is already registered !!!");
        }
        String qrCodeText = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        User user = new User(
                dto.getUsername(),
                encoder.encode(dto.getPassword()),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getMiddleName(),
                dto.getBirthDate(),
                dto.getGender(),
                dto.getPhoneNumber(),
                dto.getAddress(),
                dto.getPosition(),
                dto.getRank(),
                dto.getRole(),
                qrCodeText
        );
        User savedUser = repository.save(user);

        return savedUser.getId();
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAllBy()
                .parallelStream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            fileStorageService.deleteFile(user.getFile());
            fileStorageService.deleteFile(user.getPicture());

            user.setUsername(user.getUsername() + UUID.randomUUID());
            user.setDeleted(true);
            repository.save(user);
        }
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public UserDto toDto(UserDtoProjection projection) {
        String fileDownloadUrl = baseUrl + "/file/download/";
        String fileUrl = Objects.nonNull(projection.getFileId()) ? fileDownloadUrl + "file/" + projection.getFileId() : "";
        String pictureUrl = Objects.nonNull(projection.getPictureId()) ? fileDownloadUrl + "picture/" + projection.getPictureId() : "";

        return new UserDto(
                projection.getId(),
                projection.getUsername(),
                projection.getFirstname(),
                projection.getLastname(),
                projection.getMiddleName(),
                projection.getBirthdate(),
                projection.getGender(),
                projection.getPhoneNumber(),
                projection.getAddress(),
                projection.getPosition(),
                projection.getRank(),
                projection.getRole(),
                projection.getQrCodeText(),
                fileUrl,
                pictureUrl
        );
    }

}
