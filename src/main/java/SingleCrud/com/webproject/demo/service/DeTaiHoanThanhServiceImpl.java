package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.DeTaiHoanThanh;
import SingleCrud.com.webproject.demo.repository.DeTaiHoanThanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeTaiHoanThanhServiceImpl implements DeTaiHoanThanhService {

    private final DeTaiHoanThanhRepository  deTaiHoanThanhRepository;

    @Autowired
    public DeTaiHoanThanhServiceImpl(DeTaiHoanThanhRepository deTaiHoanThanhRepository) {
        this.deTaiHoanThanhRepository = deTaiHoanThanhRepository;
    }

    @Override
    public List<DeTaiHoanThanh> findAll() {
        return deTaiHoanThanhRepository.findAll();
    }

    @Override
    public DeTaiHoanThanh findByID(String id) {
        List<DeTaiHoanThanh> hoanThanhList = this.findAll();
        for (int i = 0; i < hoanThanhList.size(); i++) {
            if (hoanThanhList.get(i).getID().equals(id)) {
                return hoanThanhList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteByIdDeTai(String idDeTai) {
        List<String> idlist = deTaiHoanThanhRepository.findAll().stream().map(element -> {
            return element.getIDDeTai().equals(idDeTai) ? element.getID() : "";
        }).filter(element -> {
            return !element.equals("");
        }).collect(Collectors.toList());

        for (int i = 0; i < idlist.size(); i++) {
            deTaiHoanThanhRepository.deleteById(idlist.get(i));
        }
    }
}
