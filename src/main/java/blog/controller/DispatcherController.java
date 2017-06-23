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

    @GetMapping("/admin/index")
    public String showAllArticle(Model model){
        model.addAttribute("userInfo",userService.shwoUserInfo("weber"));
        model.addAttribute("page_index",true);
        return "/admin/index";
    }

    @GetMapping(value = "/admin/login")
    String loginIn(){
        if(AuthorityTool.isAuthenticated())
          return "redirect:/admin/index";
        return "/admin/login";
    }

    @GetMapping("/me")
    String me(Model model){
        model.addAttribute("me",userService.shwoUserInfo(AuthorityTool.getPrincipal()));
        model.addAttribute("page_me",true);
        return "me";
    }

    @GetMapping("/password")
    String changepassword(Model model){
        model.addAttribute("page_password",true);
        return "password";
    }

    @GetMapping("/admin/blog-list")
    String adminBlog(Model model){
        model.addAttribute("blog",articleService.showAllArticle());
        model.addAttribute("page_adminblog",true);
        return "/admin/blog-list";
    }

    @GetMapping("/adminFiles")
    String adminFiles(Model model){
        model.addAttribute("page_adminFiles",true);
        return "adminFiles";
    }

    @GetMapping("/adminTag")
    String adminTag(Model model){
        model.addAttribute("taglist", tagService.showTag());
        model.addAttribute("page_tag",true);
        return "adminTag";
    }

    @GetMapping("/adminComment")
    String adminCom(Model model){
        model.addAttribute("comlist",articleService.showAllComment());
        model.addAttribute("page_com",true);
        return "adminComment";
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

}