package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.DeTaiDangThucHien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeTaiDangThucHienRepository extends JpaRepository<DeTaiDangThucHien, String> {
}
