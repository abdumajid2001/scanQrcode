package abj.scanQrcode.entity;

import abj.scanQrcode.enums.Position;
import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.Gender;
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
@Entity
@Table(name = "users")
@Getter
@Setter
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

    @Column(name = "qr_code_text")
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

    public User(Long id, String username, UserRole role, String firstName, String lastName, LocalDate birthDate, Rank rank, FileStorage file, FileStorage picture) {
        super(id);
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.rank = rank;
        this.file = file;
        this.picture = picture;
    }

    public User(Long id, String username, UserRole role, String firstName, String lastName, LocalDate birthDate, Rank rank, String qrCodeText, FileStorage file, FileStorage picture) {
        super(id);
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.rank = rank;
        this.qrCodeText = qrCodeText;
        this.file = file;
        this.picture = picture;
    }

}
