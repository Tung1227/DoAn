package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.NghiepVu;

import java.util.List;

public interface NghiepVuService {
    List<NghiepVu> findAll();

    List<NghiepVu> findByIdUser(String idUser);
}
