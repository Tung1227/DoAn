package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.BaiBao;
import SingleCrud.com.webproject.demo.repository.BaiBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaiBaoServiceImpl implements  BaiBaoService {
    private final BaiBaoRepository baiBaoRepository;

    @Autowired
    public BaiBaoServiceImpl(BaiBaoRepository baiBaoRepository) {
        this.baiBaoRepository = baiBaoRepository;
    }

    @Override
    public List<BaiBao> findAll() {
        return baiBaoRepository.findAll();
    }

    @Override
    public void deleteByIdDeTai(String idDeTai) {
        List<String> idBaiBao = baiBaoRepository.findAll().stream().map(element -> element.getIDDeTai().equals(idDeTai) ? element.getIDBaiBao() : "").filter(element -> {
            return !element.equals("");
        }).collect(Collectors.toList());

        for (int i = 0; i < idBaiBao.size(); i++) {
            baiBaoRepository.deleteById(idBaiBao.get(i));
        }
    }

    @Override
    public BaiBao findByIdDeTai(String idDeTai) {
        List<BaiBao> baiBaoList = this.findAll();
        BaiBao baiBao = null;
        for (int i = 0; i < baiBaoList.size(); i++) {
            if (baiBaoList.get(i).getIDDeTai().equals(idDeTai)) {
                baiBao = baiBaoList.get(i);
            }
        }
        return baiBao;
    }

    @Override
    public String themBaiBao(BaiBao baiBao, String idDeTai, String noidung) {
        if(baiBao == null) {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            baiBao = new BaiBao(idDeTai, noidung, format.format(date));
            baiBaoRepository.save(baiBao);
        } else {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            baiBao.setNoiDung(noidung);
            baiBao.setNgayDang(format.format(date));
            baiBaoRepository.save(baiBao);
        }
        return baiBao.getIDBaiBao();
    }
}
