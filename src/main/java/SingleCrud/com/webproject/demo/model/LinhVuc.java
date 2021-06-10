package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"LinhVuc\"", schema = "dbo")
public class LinhVuc {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"IDLinhVuc\"")
    private String IDLinhVuc;

    @Column(name = "\"TenLinhVuc\"")
    private String TenLinhVuc;

    public String getTenLinhVuc() {
        return TenLinhVuc;
    }

    public void setTenLinhVuc(String tenLinhVuc) {
        TenLinhVuc = tenLinhVuc;
    }

    public LinhVuc() {

    }

    public LinhVuc(String tenLinhVuc) {
        TenLinhVuc = tenLinhVuc;
    }
}
