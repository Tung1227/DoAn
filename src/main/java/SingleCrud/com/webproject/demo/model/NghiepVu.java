package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"NghiepVu\"", schema = "dbo")
public class NghiepVu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;

    @Column(name = "\"IDCanBo\"")
    private String IDCanBo;

    @Column(name = "\"IDLinhVuc\"")
    private String IDLinhVuc;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDCanBo() {
        return IDCanBo;
    }

    public void setIDCanBo(String IDCanBo) {
        this.IDCanBo = IDCanBo;
    }

    public String getIDLinhVuc() {
        return IDLinhVuc;
    }

    public void setIDLinhVuc(String IDLinhVuc) {
        this.IDLinhVuc = IDLinhVuc;
    }

    public NghiepVu() {
    }

    public NghiepVu(String IDCanBo, String IDLinhVuc) {
        this.IDCanBo = IDCanBo;
        this.IDLinhVuc = IDLinhVuc;
    }
}
