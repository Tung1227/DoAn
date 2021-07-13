package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.LinhVuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhVucRepository extends JpaRepository<LinhVuc, String> {
}
