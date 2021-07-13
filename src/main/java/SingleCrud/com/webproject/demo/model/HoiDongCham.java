package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"HoiDongCham\"", schema = "dbo")
public class HoiDongCham {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;

    @Column(name = "\"IDCanBo\"")
    private String IDCanBo;
    @Column(name = "\"IDDeTai\"")
    private String IDDeTai;
    @Column(name = "\"Diem\"")
    private String Diem;


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

    public String getIDDeTai() {
        return IDDeTai;
    }

    public void setIDDeTai(String IDDeTai) {
        this.IDDeTai = IDDeTai;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String Diem) {
        this.Diem = Diem;
    }

    public HoiDongCham() {

    }

    public HoiDongCham(String IDCanBo, String IDDeTai, String Diem) {
        this.IDCanBo = IDCanBo;
        this.IDDeTai = IDDeTai;
        this.Diem = Diem;
    }
}
