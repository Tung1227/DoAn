package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.DeTai;
import SingleCrud.com.webproject.demo.model.DeTaiDangThucHien;

import java.util.List;

public interface DeTaiDangThucHienService {
    List<DeTaiDangThucHien> findAll();

    void updateTienDo(DeTaiDangThucHien  deTaiDangThucHien, String idDeTai , String newTiendo);

    DeTaiDangThucHien findByIdDeTai(String idDeTai);

    void deleteByIdDeTai(String idDeTai);
}
