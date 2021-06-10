package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.ChuyenMon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuyenMonRepository extends JpaRepository<ChuyenMon, String> {
}
