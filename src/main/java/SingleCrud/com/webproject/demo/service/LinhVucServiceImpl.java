package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.LinhVuc;
import SingleCrud.com.webproject.demo.repository.LinhVucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinhVucServiceImpl implements LinhVucService {

    private final LinhVucRepository linhvucRepository;

    @Autowired
    public LinhVucServiceImpl(LinhVucRepository linhvucRepository) {
        this.linhvucRepository = linhvucRepository;
    }


    @Override
    public List<LinhVuc> findAll() {
        return linhvucRepository.findAll();
    }

    @Override
    public LinhVuc findByID(String idLinhVuc) {
        List<LinhVuc> linhVucList = this.findAll();
        for (int i = 0; i < linhVucList.size(); i++) {
            if (linhVucList.get(i).getIDLinhVuc().equals(idLinhVuc)) {
                return linhVucList.get(i);
            }
        }
        return null;
    }

    @Override
    public LinhVuc findByName(String tenLinhVuc) {
        List<LinhVuc> linhVucList = this.findAll();
        for (int i = 0; i < linhVucList.size(); i++) {
            if (linhVucList.get(i).getTenLinhVuc().toLowerCase().equals(tenLinhVuc.toLowerCase())) {
                return linhVucList.get(i);
            }
        }
        return null;
    }
}
