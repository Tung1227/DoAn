package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.NghiepVu;
import SingleCrud.com.webproject.demo.repository.NghiepVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NghiepVuServiceImpl implements NghiepVuService {

    private final NghiepVuRepository nghiepVuRepository;

    @Autowired
    public NghiepVuServiceImpl(NghiepVuRepository nghiepVuRepository) {
        this.nghiepVuRepository = nghiepVuRepository;
    }



    @Override
    public List<NghiepVu> findAll() {
        return nghiepVuRepository.findAll();
    }

    @Override
    public List<NghiepVu> findByIdUser(String idUser) {
        List<NghiepVu> nghiepVuList = this.findAll();
        List<NghiepVu> nghiepVucCuaUser = new ArrayList<NghiepVu>();
        for (int i = 0; i < nghiepVuList.size(); i++) {
            if (nghiepVuList.get(i).getIDCanBo().equals(idUser)) {
                nghiepVucCuaUser.add(nghiepVuList.get(i));
            }
        }
        return nghiepVucCuaUser;
    }
}
