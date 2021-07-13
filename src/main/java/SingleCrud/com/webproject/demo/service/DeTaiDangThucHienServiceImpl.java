package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.DeTai;
import SingleCrud.com.webproject.demo.model.DeTaiDangThucHien;
import SingleCrud.com.webproject.demo.repository.DeTaiDangThucHienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeTaiDangThucHienServiceImpl implements DeTaiDangThucHienService {

    private final DeTaiDangThucHienRepository deTaiDangThucHienRepository;

    @Autowired
    public DeTaiDangThucHienServiceImpl(DeTaiDangThucHienRepository deTaiDangThucHienRepository) {
        this.deTaiDangThucHienRepository = deTaiDangThucHienRepository;
    }


    @Override
    public List<DeTaiDangThucHien> findAll() {
        return deTaiDangThucHienRepository.findAll();
    }

    @Override
    public void updateTienDo(DeTaiDangThucHien deTaiDangThucHien, String idDetai , String newTiendo) {
        deTaiDangThucHien.setTienDo(newTiendo);
        deTaiDangThucHien.setIDDeTai(idDetai);
        deTaiDangThucHienRepository.save(deTaiDangThucHien);
    }

    @Override
    public DeTaiDangThucHien findByIdDeTai(String idDeTai) {
        List<DeTaiDangThucHien> deTaiDangThucHiens = this.findAll();
        DeTaiDangThucHien deTaiDangThucHien = new DeTaiDangThucHien();
        for (int i = 0 ; i < deTaiDangThucHiens.size(); i++) {
            if (deTaiDangThucHiens.get(i).getIDDeTai().equals(idDeTai)) {
                deTaiDangThucHien = deTaiDangThucHiens.get(i);
                break;
            }
        }
        return deTaiDangThucHien;
    }

    @Override
    public void deleteByIdDeTai(String idDeTai) {
        List<String> idDeTaiDangThucHien = deTaiDangThucHienRepository.findAll().stream().map(element -> {
            return element.getIDDeTai().equals(idDeTai) ? element.getID() : "";
        }).filter(element -> {
            return !element.equals("");
        }).collect(Collectors.toList());

        for (int i = 0; i < idDeTaiDangThucHien.size(); i++) {
            deTaiDangThucHienRepository.deleteById(idDeTaiDangThucHien.get(i));
        }
    }
}
