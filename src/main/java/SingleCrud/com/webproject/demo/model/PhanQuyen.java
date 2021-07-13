package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"PhanQuyen\"", schema = "dbo")
public class PhanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;

    @Column(name = "\"LoaiTaiKhoan\"")
    private String LoaiTaiKhoan;

    public String getID() {
        return ID;
    }

    public String getLoaiTaiKhoan() {
        return LoaiTaiKhoan;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        LoaiTaiKhoan = loaiTaiKhoan;
    }

    public PhanQuyen() {

    }

    public PhanQuyen(String loaiTaiKhoan) {
        LoaiTaiKhoan = loaiTaiKhoan;
    }
}
