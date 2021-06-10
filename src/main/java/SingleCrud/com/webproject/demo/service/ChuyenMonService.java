package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.ChuyenMon;

import java.util.List;

public interface ChuyenMonService {
    List<ChuyenMon> findAll();

    void addChuyenMon(String idLinhVuc, String idDeTai);

    void deleteByIdDeTai(String idDeTai);
}
