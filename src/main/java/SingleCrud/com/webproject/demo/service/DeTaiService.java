package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.DeTai;

import java.util.List;

public interface DeTaiService {
    List<DeTai> findAll();

    List<DeTai> findByStatus(String status);

    DeTai findById(String idDeTai);

    void changeStatus(String idDeTai, String status);

    void update(DeTai deTai, DeTai newDeTai);

    String addDeTai(DeTai newDeTai);

    void updateNguoiHuongDan(DeTai deTai, String idnguoihuongdan);

    void updateStatus(DeTai deTai, String status);

    void deleteById(String idDeTai);
}
