package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.LinhVuc;
import SingleCrud.com.webproject.demo.model.NghiepVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NghiepVuRepository extends JpaRepository<NghiepVu, String> {
}
