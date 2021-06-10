package SingleCrud.com.webproject.demo.model;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"User\"", schema = "dbo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;

    @Column(name = "\"TaiKhoan\"")
    private String TaiKhoan;

    @Column(name = "\"MatKhau\"")
    private String MatKhau;

    @Column(name = "\"Ten\"")
    private String Ten;

    @Column(name = "\"NgaySinh\"")
    private String NgaySinh;

    @Column(name = "\"GioiTinh\"")
    private Boolean GioiTinh;

    @Column(name = "\"Gmail\"")
    private String Gmail;

    @Column(name = "\"SDT\"")
    private String SDT;

    @Column(name = "\"DiaChi\"")
    private String DiaChi;

    @Column(name = "\"Role\"")
    private String Role;

    @Column(name = "\"TrangThai\"")
    private String TrangThai;

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public User() {

    }

    public User(String taiKhoan, String matKhau, String ten, String ngaySinh, boolean gioiTinh, String gmail, String SDT, String diaChi, String role) {
        this.TaiKhoan = taiKhoan;
        this.MatKhau = matKhau;
        this.Ten =  ten;
        this.NgaySinh = ngaySinh;
        this.GioiTinh = gioiTinh;
        this.Gmail = gmail;
        this.SDT = SDT;
        this.DiaChi = diaChi;
        this.Role = role;
        this.TrangThai = "mo";
    }


}
