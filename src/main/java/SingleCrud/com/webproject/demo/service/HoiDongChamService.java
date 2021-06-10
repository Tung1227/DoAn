package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.HoiDongCham;

import java.util.List;

public interface HoiDongChamService {
    List<HoiDongCham> findAll();

    void updateListByUser(String idDeTai, List<String> idHoiDongChamCuaDeTai);

    void deleteByIdDeTai(String idDeTai);
}
