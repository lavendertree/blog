package blog.controller;

import blog.service.ArticleService;
import blog.service.TagService;
import blog.utils.entity.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by weber on 2017/6/1
 */
@RestController
public class BlogController {
    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @PostMapping("/addBlog")
    String addBlog(String title,String description,String content,Integer classId){
        if(title.equals(""))
            return "redirect:/addBlog.html";
        articleService.saveArticle(title,description,content,classId);
        return "redirect:/me";
    }

    @DeleteMapping("/deleteBlog/{id}")
    void deleteBlog(@PathVariable("id") Integer id){
        articleService.deleteArticle(id);
    }

    @PatchMapping ("/updateBlog/{id}")
    void updateBlog(@PathVariable("id") Integer id ,String title,String description,String content,Integer classId){
        articleService.updateArticle(id,title,description,content,classId);
    }

    @PostMapping("/sendComment")
    void addComment(Integer titleId,String vistorname,String content){
        articleService.addComment(titleId,vistorname,content);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    void deleteComment(@PathVariable("commentId") Integer commentId){
        articleService.deleteComment(commentId);
    }

    @PostMapping("/addReply")
    void  addReply(Integer commentId,String reply){
        articleService.addReply(commentId,reply);
    }

    @DeleteMapping("/deleteReply/{replyId}")
    void deleteReply(@PathVariable("replyId") Integer replyId){
        articleService.deleteReply(replyId);
    }

    @PostMapping("/addTag")
    void addTag(String tagname){
        tagService.addTag(tagname);
    }

    @DeleteMapping("/deleteTag/{id}")
    void deleteTag(@PathVariable("id") Integer id){
        tagService.deleteTag(id);
    }

    @GetMapping("/showTag")
    List<Classification> showTag(){
        return  tagService.showTag();
    }

}
