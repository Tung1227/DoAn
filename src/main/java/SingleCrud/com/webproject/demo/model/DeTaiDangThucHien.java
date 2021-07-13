package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"DeTaiDangThucHien\"", schema = "dbo")
public class DeTaiDangThucHien {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;

    @Column(name = "\"IDDetai\"")
    private String IDDeTai;

    @Column(name = "\"TienDo\"")
    private String TienDo;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDDeTai() {
        return IDDeTai;
    }

    public void setIDDeTai(String IDDeTai) {
        this.IDDeTai = IDDeTai;
    }

    public String getTienDo() {
        return TienDo;
    }

    public void setTienDo(String tienDo) {
        TienDo = tienDo;
    }

    public DeTaiDangThucHien() {
    }

    public DeTaiDangThucHien(String IDDeTai, String tienDo) {
        this.IDDeTai = IDDeTai;
        TienDo = tienDo;
    }
}
