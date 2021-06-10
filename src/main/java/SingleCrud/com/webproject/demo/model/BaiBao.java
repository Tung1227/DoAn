package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"BaiBao\"", schema = "dbo")
public class BaiBao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"IDBaiBao\"")
    private String IDBaiBao;

    @Column(name = "\"IDDeTai\"")
    private String IDDeTai;

    @Column(name = "\"NoiDung\"")
    private String NoiDung;

    @Column(name = "\"NgayDang\"")
    private String NgayDang;

    public String getIDBaiBao() {
        return IDBaiBao;
    }

    public void setIDBaiBao(String IDBaiBao) {
        this.IDBaiBao = IDBaiBao;
    }

    public String getIDDeTai() {
        return IDDeTai;
    }

    public void setIDDeTai(String IDDeTai) {
        this.IDDeTai = IDDeTai;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }

    public BaiBao() {
    }

    public BaiBao(String IDDeTai, String noiDung, String ngayDang) {
        this.IDDeTai = IDDeTai;
        NoiDung = noiDung;
        NgayDang = ngayDang;
    }
}
