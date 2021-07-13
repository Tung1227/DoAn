package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.ChuyenMon;
import SingleCrud.com.webproject.demo.repository.ChuyenMonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChuyenMonServiceImpl implements ChuyenMonService {


    private final ChuyenMonRepository chuyenmonRepository;

    @Autowired
    public ChuyenMonServiceImpl(ChuyenMonRepository chuyenmonRepository) {
        this.chuyenmonRepository = chuyenmonRepository;
    }

    @Override
    public List<ChuyenMon> findAll() {
        return chuyenmonRepository.findAll();
    }

    @Override
    public void addChuyenMon(String idLinhVuc, String idDeTai) {
        ChuyenMon chuyenMon = new ChuyenMon(idLinhVuc, idDeTai);
        chuyenmonRepository.save(chuyenMon);
    }

    @Override
    public void deleteByIdDeTai(String idDeTai) {
        List<String> idChuyenMon = chuyenmonRepository.findAll().stream().map(element -> {
            return element.getIDDeTai().equals(idDeTai) ? element.getID() : "";
        }).filter(element -> {
            return !element.equals("");
        }).collect(Collectors.toList());

        for (int i = 0; i < idChuyenMon.size(); i++) {
            chuyenmonRepository.deleteById(idChuyenMon.get(i));
        }
    }
}
