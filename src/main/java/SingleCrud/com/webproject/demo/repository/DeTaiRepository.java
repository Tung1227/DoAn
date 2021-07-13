package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.DeTai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeTaiRepository  extends JpaRepository<DeTai, String> {

}
