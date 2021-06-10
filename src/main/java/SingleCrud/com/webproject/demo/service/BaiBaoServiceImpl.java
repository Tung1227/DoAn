package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.BaiBao;
import SingleCrud.com.webproject.demo.repository.BaiBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<String> idBaiBao = baiBaoRepository.findAll().stream().map(element -> {
            return element.getIDDeTai().equals(idDeTai) ? element.getIDBaiBao() : "";
        }).filter(element -> {
            return !element.equals("");
        }).collect(Collectors.toList());

        for (int i = 0; i < idBaiBao.size(); i++) {
            baiBaoRepository.deleteById(idBaiBao.get(i));
        }
    }
}
