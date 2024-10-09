package abj.scanQrcode.service.impl;

import abj.scanQrcode.dto.FileStorageDto;
import abj.scanQrcode.entity.FileStorage;
import abj.scanQrcode.entity.User;
import abj.scanQrcode.exception.NotFoundException;
import abj.scanQrcode.repository.FileStorageRepository;
import abj.scanQrcode.service.FileStorageService;
import abj.scanQrcode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageRepository repository;
    private final UserService userService;
    private Path rootLocation;

    {
        String directoryPath = System.getProperty("user.dir") + File.separator + "pictures";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully at: " + directory.getAbsolutePath());
            } else {
                System.out.println("Failed to create the directory.");
                System.exit(0);
            }
        } else {
            System.out.println("Directory already exists at: " + directory.getAbsolutePath());
        }
        rootLocation = Path.of(directoryPath);
    }

    public FileStorage get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found"));
    }

    public void deleteFile(FileStorage file) {
        file.setDeleted(true);
        repository.save(file);

        Path path = rootLocation.resolve(file.getGeneratedName());
        path.toFile().delete();
    }

    public FileStorage uploadToMemory(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String format = StringUtils.getFilenameExtension(originalFilename);
        String generateName = UUID.randomUUID().toString().replace("-", "") + "." + format;
        Path path = Paths.get(rootLocation.toString(), generateName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new FileStorage(originalFilename, generateName, file.getContentType());
    }


    @Override
    public void upload(Long userId, MultipartFile file, MultipartFile picture) {
        User user = userService.findById(userId);

        if (Objects.nonNull(user.getFile())) {
            deleteFile(user.getFile());
        }
        user.setFile(uploadToMemory(file));
        user.setPicture(uploadToMemory(picture));
        userService.save(user);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path path = rootLocation.resolve(filename);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public FileStorageDto download(Long id) {
        FileStorage file = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found"));
        String headerValue = "inline; filename=\"" + file.getOriginalName() + "\"";
        Resource resource = loadAsResource(file.getGeneratedName());

        return new FileStorageDto(file.getType(), resource, headerValue);
    }

}
