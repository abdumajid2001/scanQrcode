package abj.scanQrcode.repository;

import abj.scanQrcode.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {


}
