package abj.scanQrcode.entity;

import abj.scanQrcode.enums.Gender;
import abj.scanQrcode.enums.Position;
import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users",
        indexes = {
                @Index(name = "unique_qr_code_text", columnList = "qrCodeText", unique = true)
        }
)
@SQLRestriction("is_deleted = false")
public class User extends Auditable {

    @Column(unique = true)
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String qrCodeText;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileStorage file;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_id")
    private FileStorage picture;

    public User(String username,
                String password,
                String lastName,
                String firstName,
                String middleName,
                LocalDate birthDate,
                Gender gender,
                String phoneNumber,
                String address,
                Position position,
                Rank rank,
                UserRole role,
                String qrCodeText) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.position = position;
        this.rank = rank;
        this.role = role;
        this.qrCodeText = qrCodeText;
    }

}

