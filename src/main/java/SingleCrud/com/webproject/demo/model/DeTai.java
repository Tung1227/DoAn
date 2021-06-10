package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"DeTai\"", schema = "dbo")
public class DeTai {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"IDDetai\"")
    private String IDDeTai;

    @Column(name = "\"TenDeTai\"")
    private String TenDeTai;

    @Column(name = "\"MoTa\"")
    private String MoTa;

    @Column(name = "\"IDNguoihuongdan\"")
    private String IDNguoihuongdan;

    @Column(name = "\"TrangThai\"")
    private String TrangThai;

    public String getIDDeTai() {
        return IDDeTai;
    }

    public void setIDDeTai(String IDDeTai) {
        this.IDDeTai = IDDeTai;
    }

    public String getTenDeTai() {
        return TenDeTai;
    }

    public void setTenDeTai(String tenDeTai) {
        TenDeTai = tenDeTai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getIDNguoihuongdan() {
        return IDNguoihuongdan;
    }

    public void setIDNguoihuongdan(String IDNguoihuongdan) {
        this.IDNguoihuongdan = IDNguoihuongdan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public DeTai() {

    }

    public DeTai(String tenDeTai, String moTa, String IDNguoihuongdan) {
        this.TenDeTai = tenDeTai;
        this.MoTa = moTa;
        this.IDNguoihuongdan = IDNguoihuongdan;
        this.TrangThai = "dangxet";
    }
}
