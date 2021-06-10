package SingleCrud.com.webproject.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "\"ChuyenMon\"", schema = "dbo")
public class ChuyenMon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "\"ID\"")
    private String ID;

    @Column(name = "\"IDLinhVuc\"")
    private String IDLinhVuc;

    @Column(name = "\"IDDeTai\"")
    private String IDDeTai;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDLinhVuc() {
        return IDLinhVuc;
    }

    public void setIDLinhVuc(String IDLinhVuc) {
        this.IDLinhVuc = IDLinhVuc;
    }

    public String getIDDeTai() {
        return IDDeTai;
    }

    public void setIDDeTai(String IDDeTai) {
        this.IDDeTai = IDDeTai;
    }

    public ChuyenMon() {

    }

    public ChuyenMon(String IDLinhVuc, String IDDeTai) {
        this.IDLinhVuc = IDLinhVuc;
        this.IDDeTai = IDDeTai;
    }
}
