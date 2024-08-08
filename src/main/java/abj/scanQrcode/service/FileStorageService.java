package abj.scanQrcode.service;

import abj.scanQrcode.dto.FileStorageDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    void upload(Long userId,MultipartFile file, MultipartFile picture);

    FileStorageDto download(Long fileId);

}
