package abj.scanQrcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_storages")
@Setter
@Getter
@SQLRestriction("is_deleted = false")
public class FileStorage extends Auditable {

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String generatedName;

    private String type;

}
