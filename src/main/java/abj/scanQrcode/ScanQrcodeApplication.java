package abj.scanQrcode;

import abj.scanQrcode.dto.auth.AuthenticationRequest;
import abj.scanQrcode.dto.auth.UserRegisterDto;
import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.UserRole;
import abj.scanQrcode.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ScanQrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScanQrcodeApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(UserService service) {
//        return (args -> service.register(new UserRegisterDto("username","123", UserRole.ADMIN,"Abdumajid","Abdullatipov", LocalDate.now(), Rank.POLKOVNIK)));
//    }

}
