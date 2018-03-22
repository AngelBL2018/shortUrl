package shorturl.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shorturl.demo.model.Url;

public interface UrlRepository extends JpaRepository<Url,Integer> {

    Url findUrlByCode(String code);
}
