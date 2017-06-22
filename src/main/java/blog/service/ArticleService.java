package blog.service;

import blog.dao.ArticleDao;
import blog.dao.ClassDao;
import blog.dao.CommentDao;
import blog.dao.ReplyDao;
import blog.utils.entity.Article;
import blog.utils.entity.Classification;
import blog.utils.entity.UserReply;
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

    @Autowired
    private ReplyDao replyDao;

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
    public List<Article> showAllArticle(){
        return articleDao.findAll();

    }

    //根据标签获取对应的博文
    public List<Article> showTagArticle(Integer classID){
       Classification classification= classDao.findOne(classID);
        List<Article> articleRepositoryList = articleDao.findByClassificationByClassId(classification);
        return articleRepositoryList;
    }

    //获取最新的博文
    public Page<Article> showRecentArticle(){
        Pageable pageable= (Pageable) new PageRequest(0,10);
        return articleDao.showLastestBlog( pageable);
    }

    //根据ID号获取对应博文
    public Article showOneArticle(Integer titleId){
        return  articleDao.findOne(titleId);
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

    //添加一条回复
    public void addReply(Integer commentId,String reply){
        UserReply userreply=new UserReply();
        userreply.setVistorCommentByCommentId(commentDao.findOne(commentId));
        userreply.setReply(reply);
        userreply.setReplyTime(new Timestamp(System.currentTimeMillis()));
        replyDao.save(userreply);
    }

    //删除对应回复
    public void deleteReply(Integer replyId){
        replyDao.delete(replyId);
    }


    //根据评论ID返回对应的回复
    public UserReply showReply(Integer commentID){
        return replyDao.findOne(commentID);
    }
}

