package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.BaiBao;

import java.util.List;

public interface BaiBaoService {
    List<BaiBao> findAll();

    void deleteByIdDeTai(String idDeTai);
}
