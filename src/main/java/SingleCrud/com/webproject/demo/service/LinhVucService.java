package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.LinhVuc;

import java.util.List;

public interface LinhVucService {
    List<LinhVuc> findAll();

    LinhVuc findByID(String idLinhVuc);
}
