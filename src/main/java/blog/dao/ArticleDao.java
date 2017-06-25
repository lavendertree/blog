package blog.dao;

import blog.utils.entity.Article;
import blog.utils.entity.Classification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by weber on 2017/5/21.
 */
public interface ArticleDao extends JpaRepository<Article,Integer> {

   //根据类别展示相应博客
     Page<Article> findByClassificationByClassId(Classification classification,Pageable pageable);

    //展示最新的博文
    @Query( "select article from Article article  order by article.time desc")
    Page<Article> showLastestBlog(Pageable pageable);

    //根据ID号获取相应的博文
    public Article findByTitleId(Integer titleId);

    public void deleteByTitleId(Integer titleId);

    public List<Article> findByTitleContaining(String title);

}
