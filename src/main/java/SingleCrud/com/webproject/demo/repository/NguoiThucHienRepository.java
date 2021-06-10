package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.NguoiThucHien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiThucHienRepository extends JpaRepository<NguoiThucHien, String> {
}
