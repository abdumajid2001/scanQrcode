package abj.scanQrcode.controller;

import abj.scanQrcode.dto.FileStorageDto;
import abj.scanQrcode.dto.responce.DataDto;
import abj.scanQrcode.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file/")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService service;

    @PostMapping(value = "upload/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataDto<String>> upload(@PathVariable Long userId,
                                                  @RequestParam("file") MultipartFile file,
                                                  @RequestParam("picture") MultipartFile picture
    ) {
        service.upload(userId, file, picture);
        return ResponseEntity.ok(new DataDto<>("File uploaded successfully: "));
    }

    @GetMapping("download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable Long fileId) throws IOException {
        FileStorageDto fileDto = service.download(fileId);
        Resource file = fileDto.getResource();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDto.getType()))
                .contentLength(file.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, fileDto.getHeaderValue())
                .body(file);
    }

}
