package abj.scanQrcode;

import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScanQrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScanQrcodeApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(UserService service) {
//        return (args -> service.register(new AuthenticationRequest("username", "123")));
//    }

}
