package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"NguoiThucHien\"", schema = "dbo")
public class NguoiThucHien {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;


    @Column(name = "\"IDDeTai\"")
    private String IDDeTai;
    @Column(name = "\"IDNguoiThucHien\"")
    private String IDNguoiThucHien;

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

    public String getIDNguoiThucHien() {
        return IDNguoiThucHien;
    }

    public void setIDNguoiThucHien(String IDNguoiThucHien) {
        this.IDNguoiThucHien = IDNguoiThucHien;
    }

    public NguoiThucHien() {
    }

    public NguoiThucHien(String IDDeTai, String IDNguoiThucHien) {
        this.IDDeTai = IDDeTai;
        this.IDNguoiThucHien = IDNguoiThucHien;
    }
}
