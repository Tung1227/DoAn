package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.BaiBao;

import java.util.List;

public interface BaiBaoService {
    List<BaiBao> findAll();

    void deleteByIdDeTai(String idDeTai);

    BaiBao findByIdDeTai(String idDeTai);

    String themBaiBao(BaiBao baiBao, String idDeTai, String noidung);
}
