package abj.scanQrcode;

import abj.scanQrcode.dto.user.UserRegisterDto;
import abj.scanQrcode.enums.Gender;
import abj.scanQrcode.enums.Position;
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
//        return (args -> service.register(
//                new UserRegisterDto(
//                        "username",
//                        "123",
//                        "Abdumajid",
//                        "Abdullatipov",
//                        "Islomjon o'g'li",
//                        LocalDate.now(),
//                        Gender.ERKAK,
//                        "+998900032869",
//                        "Andijon viloyati",
//                        Position.DASTURCHI,
//                        Rank.POLKOVNIK,
//                        UserRole.ADMIN
//                )));
//    }

}
