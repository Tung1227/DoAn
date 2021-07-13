package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.HoiDongCham;
import SingleCrud.com.webproject.demo.repository.HoiDongChamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HoiDongChamServiceImpl implements HoiDongChamService {

    private final HoiDongChamRepository hoiDongChamRepository;

    @Autowired
    public HoiDongChamServiceImpl(HoiDongChamRepository hoiDongChamRepository) {
        this.hoiDongChamRepository = hoiDongChamRepository;
    }


    @Override
    public List<HoiDongCham> findAll() {
        return hoiDongChamRepository.findAll();
    }

    @Override
    public void updateListByUser(String idDeTai, List<String> idHoiDongChamCuaDeTai) {
        this.deleteByIdDeTai(idDeTai);
        for (int i = 0; i < idHoiDongChamCuaDeTai.size(); i++) {
            HoiDongCham newHoiDongCham = new HoiDongCham(idHoiDongChamCuaDeTai.get(i), idDeTai, null);
            hoiDongChamRepository.save(newHoiDongCham);
        }

    }

    @Override
    public void deleteByIdDeTai(String idDeTai) {
        List<HoiDongCham> hoiDongChamList = this.findAll();
        for (int i = 0; i < hoiDongChamList.size(); i++) {
            if (hoiDongChamList.get(i).getIDDeTai().equals(idDeTai)) {
                hoiDongChamRepository.delete(hoiDongChamList.get(i));
            }
        }
    }

    @Override
    public HoiDongCham findByDeTaiAndUser(String idDeTai, String idCanBo) {
        List<HoiDongCham> hoiDongChamList = this.findAll();
        Optional<HoiDongCham> optional = hoiDongChamList.stream().filter(element -> element.getIDDeTai().equals(idDeTai))
                                                                    .filter(element -> element.getIDCanBo().equals(idCanBo))
                                                                    .findFirst();
        HoiDongCham hoiDongCham = null;
        if (optional.isPresent()) {
            hoiDongCham = optional.get();
        }
        return hoiDongCham;
    }

    @Override
    public void updateDiem(HoiDongCham hoiDongCham, String diem) {
        hoiDongCham.setDiem(diem);
        hoiDongChamRepository.save(hoiDongCham);
    }

    @Override
    public List<HoiDongCham> findByIdDeTai(String idDeTai) {
        List<HoiDongCham> hoiDongChamList = this.findAll();
        List<HoiDongCham> deTaiCham = hoiDongChamList.stream().filter(element ->
                element.getIDDeTai().equals(idDeTai)).collect(Collectors.toList());
        return deTaiCham;
    }


}
