package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.NguoiThucHien;
import SingleCrud.com.webproject.demo.repository.NguoiThucHienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NguoiThucHienServiceImpl implements NguoiThucHienService {

    private final NguoiThucHienRepository nguoiThucHienRepository;

    @Autowired
    public NguoiThucHienServiceImpl(NguoiThucHienRepository nguoiThucHienRepository) {
        this.nguoiThucHienRepository = nguoiThucHienRepository;
    }


    @Override
    public List<NguoiThucHien> findAll() {
        return nguoiThucHienRepository.findAll();
    }

    @Override
    public String findIDNguoiThucHien(String idDeTai) {
        List<NguoiThucHien> nguoiThucHienList = this.findAll();
        for (int i = 0 ; i < nguoiThucHienList.size(); i++) {
            if (nguoiThucHienList.get(i).getIDDeTai().equals(idDeTai)){
                return nguoiThucHienList.get(i).getIDNguoiThucHien();
            }
        }
        return null;
    }

    @Override
    public void add(NguoiThucHien nguoiThucHien) {
        nguoiThucHienRepository.save(nguoiThucHien);
    }

    @Override
    public List<NguoiThucHien> findDeTaiByIDUser(String idUser) {
        List<NguoiThucHien> nguoiThucHienList = this.findAll();
        List<NguoiThucHien> deTaiCuaUser = new ArrayList<NguoiThucHien>();
        for (int i = 0; i < nguoiThucHienList.size(); i++) {
            if (nguoiThucHienList.get(i).getIDNguoiThucHien().equals(idUser)) {
                deTaiCuaUser.add(nguoiThucHienList.get(i));
            }
        }
        return deTaiCuaUser;
    }

    @Override
    public void deleteByIdDeTai(String idDeTai) {
        List<String> idNguoiThucHien = nguoiThucHienRepository.findAll().stream().map(element -> {
            return element.getIDDeTai().equals(idDeTai) ? element.getID() : "";
        }).filter(element -> {
            return !element.equals("");
        }).collect(Collectors.toList());

        for (int i = 0; i < idNguoiThucHien.size(); i++) {
            nguoiThucHienRepository.deleteById(idNguoiThucHien.get(i));
        }
    }
}
