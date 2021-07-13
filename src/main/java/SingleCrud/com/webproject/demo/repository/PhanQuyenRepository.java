package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.PhanQuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, String> {
}
