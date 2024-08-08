package abj.scanQrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.Resource;

@Getter
@AllArgsConstructor
public class FileStorageDto {

    private String type;

    private Resource resource;

    private String headerValue;

}
