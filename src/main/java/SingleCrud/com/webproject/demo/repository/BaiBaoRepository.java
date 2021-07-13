package SingleCrud.com.webproject.demo.repository;

import SingleCrud.com.webproject.demo.model.BaiBao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiBaoRepository extends JpaRepository<BaiBao, String> {
}
