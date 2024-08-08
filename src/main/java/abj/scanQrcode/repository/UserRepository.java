package abj.scanQrcode.repository;

import abj.scanQrcode.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByIdAndDeletedFalse(Long id);

    @Query("select new abj.scanQrcode.entity.User(u.id,u.username,u.role,u.firstName,u.lastName,u.birthDate,u.rank,u.file,u.picture) from User u where u.qrCodeText = ?1")
    User findByQrCodeText(String qrCodeText);

    @Query("select new abj.scanQrcode.entity.User(u.id,u.username,u.role,u.firstName,u.lastName,u.birthDate,u.rank,u.qrCodeText,u.file,u.picture) from User u where u.qrCodeText = ?1")
    List<User> findAllBy();

}
