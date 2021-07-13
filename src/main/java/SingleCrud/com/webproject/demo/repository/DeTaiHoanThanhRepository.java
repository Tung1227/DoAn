package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.DeTaiHoanThanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeTaiHoanThanhRepository extends JpaRepository<DeTaiHoanThanh, String> {
}
