package abj.scanQrcode.entity;

import abj.scanQrcode.enums.Rank;
import abj.scanQrcode.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
@SQLRestriction("is_deleted = false")
public class User extends Auditable implements UserDetails {

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotEmpty(message = "FirstName may not be empty")
    @Size(min = 6, max = 32, message = "FirstName must be between 6 and 32 characters long")
    private String firstName;

    @NotEmpty(message = "LastName may not be empty")
    @Size(min = 6, max = 32, message = "LastName must be between 6 and 32 characters long")
    private String lastName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Token> tokens;

    @Column(name = "qr_code_text")
    private String qrCodeText;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileStorage file;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_id")
    private FileStorage picture;

    public User(String username, String password, UserRole role, String firstName, String lastName, LocalDate birthDate, Rank rank, String qrCodeText) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.rank = rank;
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

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
