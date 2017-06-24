package blog.controller;

import blog.service.ArticleService;
import blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weber on 2017/6/1
 */
@RestController
public class BlogController {
    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @PostMapping("/admin/writeBlog.html")
    String addBlog(String title,String description,String content,Integer classId){
        if(title.equals(""))
            return "redirect:/addBlog.html";
        articleService.saveArticle(title,description,content,classId);
        return "redirect:/me";
    }

    @PostMapping ("/admin/deleteBlog")
    void deleteBlog(Integer id){
        articleService.deleteArticle(id);
    }

    @PostMapping ("/admin/updateBlog.html")
    void updateBlog(Integer id ,String title,String description,String content,Integer classId){
        articleService.updateArticle(id,title,description,content,classId);
    }

    @PostMapping("/admin/addTag.html")
    void addTag(String tagname){
        tagService.addTag(tagname);
    }

    @PostMapping("/admin/deleteCategory")
    void deleteTag(Integer id){
        tagService.deleteTag(id);
    }

    @PostMapping("/app/sendComment")
    void addComment(Integer titleId,String vistorname,String content){
        articleService.addComment(titleId,vistorname,content);
    }


}
