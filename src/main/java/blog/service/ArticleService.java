package blog.service;

import blog.dao.ArticleDao;
import blog.dao.ClassDao;
import blog.dao.CommentDao;
import blog.utils.entity.Article;
import blog.utils.entity.Classification;
import blog.utils.entity.VistorComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private CommentDao commentDao;


    //添加一篇博文
    public void saveArticle(String title,String desctiption ,String text,Integer classID){
        Article article=new Article();
        article.setTitle(title);
        article.setDescription(desctiption);
        article.setArticleContent(text);
        article.setClassificationByClassId(classDao.findOne(classID));
        article.setTime(new Timestamp(System.currentTimeMillis()));
        articleDao.save(article);
    }

    //删除一篇博文
    public void deleteArticle(Integer id){
        articleDao.delete(id);
    }

    //修改博文
    public void updateArticle(Integer id,String title,String desctiption ,String text,Integer classID){
        Article article= articleDao.getOne(id);
        article.setTitle(title);
        article.setDescription(desctiption);
        article.setClassificationByClassId(classDao.findOne(classID));
        article.setArticleContent(text);
        article.setTime(new Timestamp(System.currentTimeMillis()));
        articleDao.save(article);
    }


    //展示所有的博文
    public Page<Article> showAllArticle(Pageable pageable){
        return articleDao.findAll(pageable);
    }

    //根据关键字搜索博文
    public List<Article> search(String keyword){
        if(!keyword.equals(""))
            return articleDao.findByTitleContaining(keyword);
        else
            return articleDao.findAll();
    }

    //根据标签获取对应的博文
    public List<Article> showTagArticle(Integer classID,Pageable pageable){
       Classification classification= classDao.findOne(classID);
        List<Article> articleRepositoryList = articleDao.findByClassificationByClassId(classification,pageable);
        return articleRepositoryList;
    }

    //获取最新的博文
    public Page<Article> showRecentArticle(){
        Pageable pageable= (Pageable) new PageRequest(0,3);
        return articleDao.showLastestBlog( pageable);
    }

    //根据ID号获取对应博文
    public Article showOneArticle(Integer id){
        return  articleDao.findOne(id);
    }

    //根据博文ID号获取对应的评论
    public  List<VistorComment> showComment(Integer titleId){
        Article article=articleDao.findByTitleId(titleId);
        return commentDao.findByArticleByTitleId(article);
    }

    //展示所有评论
    public List<VistorComment> showAllComment(){
        return commentDao.findAll();
    }

    //添加一个评论
    public void addComment(Integer titleId,String vistorname,String content){
        VistorComment comment=new VistorComment();
        comment.setArticleByTitleId(articleDao.getOne(titleId));
        comment.setVistorName(vistorname);
        comment.setComment(content);
        comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
        commentDao.save(comment);
    }

    //删除一条评论
    public void deleteComment(Integer commentId){
        commentDao.delete(commentId);
    }


}

