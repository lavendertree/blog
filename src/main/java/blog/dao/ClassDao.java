package blog.dao;

import blog.utils.entity.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by weber on 2017/5/21.
 */
public interface ClassDao extends JpaRepository<Classification,Integer> {
    void deleteByClassname(String tagname);
}
