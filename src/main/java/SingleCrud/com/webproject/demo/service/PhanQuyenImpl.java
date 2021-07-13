package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.PhanQuyen;
import SingleCrud.com.webproject.demo.repository.PhanQuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhanQuyenImpl implements PhanQuyenService{


    private final PhanQuyenRepository phanQuyenRepository;
    @Autowired
    public PhanQuyenImpl(PhanQuyenRepository phanQuyenRepository) {
        this.phanQuyenRepository = phanQuyenRepository;
    }

    @Override
    public List<PhanQuyen> findAll() {
        return phanQuyenRepository.findAll();
    }


    @Override
    public PhanQuyen findById(String id) {
        List<PhanQuyen> allRole = this.findAll();

       for (int i = 0; i < allRole.size(); i++) {
           if (allRole.get(i).getID().equals(id)) {
               return allRole.get(i);
           }
       }

       return null;
    }
}
