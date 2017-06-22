package blog.dao;

import blog.utils.entity.Article;
import blog.utils.entity.VistorComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by weber on 2017/5/21.
 */
public interface CommentDao extends JpaRepository<VistorComment,Integer> {
        List<VistorComment> findByArticleByTitleId(Article article);
}

