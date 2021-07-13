package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.PhanQuyen;

import java.util.List;

public interface PhanQuyenService {
    List<PhanQuyen> findAll();
    PhanQuyen findById(String id);
}
