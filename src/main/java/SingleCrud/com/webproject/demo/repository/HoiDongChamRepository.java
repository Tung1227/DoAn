package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.HoiDongCham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoiDongChamRepository extends JpaRepository<HoiDongCham, String> {
}
