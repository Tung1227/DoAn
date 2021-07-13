package SingleCrud.com.webproject.demo.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"DeTaiHoanThanh\"", schema = "dbo")
public class DeTaiHoanThanh {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;

    @Column(name = "\"IDDeTai\"")
    private String IDDeTai;

    @Column(name = "\"Diem\"")
    private String Diem;

    @Column(name = "\"NgayHoanThanh\"")
    private String NgayHoanThanh;

    @Column(name = "\"IDBaiBao\"")
    private String IDBaiBao;

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

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }

    public String getNgayHoanThanh() {
        return NgayHoanThanh;
    }

    public void setNgayHoanThanh(String ngayHoanThanh) {
        NgayHoanThanh = ngayHoanThanh;
    }

    public String getIDBaiBao() {
        return IDBaiBao;
    }

    public void setIDBaiBao(String IDBaiBao) {
        this.IDBaiBao = IDBaiBao;
    }

    public DeTaiHoanThanh() {
    }

    public DeTaiHoanThanh(String IDDeTai, String diem, String ngayHoanThanh, String IDBaiBao) {
        this.IDDeTai = IDDeTai;
        Diem = diem;
        NgayHoanThanh = ngayHoanThanh;
        this.IDBaiBao = IDBaiBao;
    }
}
