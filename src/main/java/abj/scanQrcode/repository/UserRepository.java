package abj.scanQrcode.repository;

import abj.scanQrcode.entity.User;
import abj.scanQrcode.projection.UserDetailsProjection;
import abj.scanQrcode.projection.UserDtoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT u.id,u.username,u.password, u.role FROM users u WHERE u.is_deleted = false AND u.username = ?1", nativeQuery = true)
    Optional<UserDetailsProjection> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = """
            SELECT u.id,
                   u.username,
                   u.first_name                        AS firstname,
                   u.last_name                         AS lastname,
                   u.middle_name                       AS middlename,
                   TO_CHAR(u.birth_date, 'YYYY-MM-DD') AS birthdate,
                   u.gender,
                   u.phone_number                      AS phonenumber,
                   u.address,
                   u.position,
                   u.role,
                   u.rank,
                   u.file_id                           AS fileid,
                   u.picture_id                        AS pictureid
            FROM users u
                     INNER JOIN file_storages f ON f.id = u.file_id
                     INNER JOIN file_storages p ON p.id = u.picture_id
            WHERE u.is_deleted = false
              AND p.is_deleted = false
              AND f.is_deleted = false
              AND u.qr_code_text = ?1""", nativeQuery = true)
    Optional<UserDtoProjection> findByQrCodeText(String qrCodeText);

    @Query(value = """
            SELECT u.id,
                   u.username,
                   u.first_name                        AS firstname,
                   u.last_name                         AS lastname,
                   u.middle_name                       AS middlename,
                   TO_CHAR(u.birth_date, 'YYYY-MM-DD') AS birthdate,
                   u.gender,
                   u.phone_number                      AS phonenumber,
                   u.address,
                   u.position,
                   u.role,
                   u.rank,
                   u.file_id                           AS fileid,
                   u.picture_id                        AS pictureid,
                   u.qr_code_text                      AS qrcodetext
            FROM users u
                     INNER JOIN file_storages f ON f.id = u.file_id
                     INNER JOIN file_storages p ON p.id = u.picture_id
            WHERE u.is_deleted = false
              AND p.is_deleted = false
              AND f.is_deleted = false""", nativeQuery = true)
    List<UserDtoProjection> findAllBy();

    @Query(value = """
            SELECT u.id,
                   u.username,
                   u.first_name                        AS firstname,
                   u.last_name                         AS lastname,
                   u.middle_name                       AS middlename,
                   TO_CHAR(u.birth_date, 'YYYY-MM-DD') AS birthdate,
                   u.gender,
                   u.phone_number                      AS phonenumber,
                   u.address,
                   u.position,
                   u.role,
                   u.rank,
                   u.file_id                           AS fileid,
                   u.picture_id                        AS pictureid
            FROM users u
                     INNER JOIN file_storages f ON f.id = u.file_id
                     INNER JOIN file_storages p ON p.id = u.picture_id
            WHERE u.is_deleted = false
                AND p.is_deleted = false
                AND f.is_deleted = false
                AND u.id = ?1""", nativeQuery = true)
    Optional<UserDtoProjection> findUserById(Long id);

}
