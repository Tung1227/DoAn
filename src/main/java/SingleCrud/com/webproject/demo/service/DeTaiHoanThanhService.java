package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.DeTaiHoanThanh;

import java.util.List;

public interface DeTaiHoanThanhService {
    List<DeTaiHoanThanh> findAll();
    DeTaiHoanThanh findByID(String id);

    void deleteByIdDeTai(String idDeTai);
}
