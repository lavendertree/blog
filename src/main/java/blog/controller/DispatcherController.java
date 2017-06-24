package blog.controller;

import blog.service.ArticleService;
import blog.service.TagService;
import blog.service.UserService;
import blog.utils.authority.AuthorityTool;
import blog.utils.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by weber on 2017/6/3.
 */

@Controller
public class DispatcherController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @GetMapping(value = "/admin/login")
    String loginIn(){
        if(AuthorityTool.isAuthenticated())
            return "redirect:/admin/index";
        return "/admin/login";
    }

    @GetMapping("/admin/index")
    public String showAllArticle(Model model){
        model.addAttribute("userInfo",userService.shwoUserInfo(AuthorityTool.getPrincipal()));
        model.addAttribute("page_index",true);
        return "/admin/index";
    }

    @GetMapping("/admin/blog-list")
    String adminBlog(Model model){
        model.addAttribute("bloglist",articleService.showAllArticle());
        model.addAttribute("categorylist",tagService.showTag());
        model.addAttribute("page_adminblog",true);
        return "/admin/blog-list";
    }

    @GetMapping("/admin/writeBlog")
    String writeBlog(Model model){
        return "/admin/writeBlog";
    }


    @GetMapping("/admin/comment-list.html")
    String adminComment(Model model){
        model.addAttribute("commentlist",articleService.showAllComment());
        return "/admin/comment-list";
    }

    @GetMapping("/admin/category.html")
    String adminCategory(Model model){
        model.addAttribute("categorylist",tagService.showTag());
        return "admin/category";
    }

    @GetMapping("/admin/changePass.html")
    String changepassword(Model model){
        model.addAttribute("page_password",true);
        return "/admin/changePass";
    }

    @GetMapping("/me")
    String me(Model model){
        model.addAttribute("me",userService.shwoUserInfo(AuthorityTool.getPrincipal()));
        model.addAttribute("page_me",true);
        return "me";
    }


    @GetMapping("/blog/{id}")
    String blog(@PathVariable Integer id, Model model){
        Article article=articleService.showOneArticle(id);
        if(article==null)
            return "404";
        model.addAttribute("blog",article);
        model.addAttribute("comment",articleService.showComment(id));
        return "blog";
    }

    @GetMapping("/tag/{tagid}")
    String blogByTag(@PathVariable Integer tagid,ModelMap modelMap){
        List<Article> blogtaglist=articleService.showTagArticle(tagid);
        modelMap.addAttribute("blogtaglist",blogtaglist);
        return "tag";
    }

    @RequestMapping("/admin/deleteComment/{commentId}")
    String deleteComment(@PathVariable("commentId") Integer commentId){
        articleService.deleteComment(commentId);
        return "redirect:/admin/comment-list.html";
    }

}