package blog.controller;

import blog.service.ArticleService;
import blog.service.TagService;
import blog.service.UserService;
import blog.utils.authority.AuthorityTool;
import blog.utils.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Pageable pageable=new PageRequest(0,10,new Sort(Sort.Direction.DESC,"time"));
        model.addAttribute("bloglist",articleService.showAllArticle(pageable));
        model.addAttribute("categorylist",tagService.showTag());
        model.addAttribute("page_adminblog",true);
        return "/admin/blog-list";
    }

    @GetMapping("/admin/writeBlog")
    String writeBlog(Model model){
        model.addAttribute("categorylist",tagService.showTag());
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

    @GetMapping("/admin/updateBlog/{id}")
    String updateBlog(@PathVariable("id") Integer id ,Model model){
        model.addAttribute("blog",articleService.showOneArticle(id));
        model.addAttribute("categorylist",tagService.showTag());
        return "/admin/updateBlog";
    }

    @GetMapping("/admin/search")
    String search(String keyword,Model model){
        model.addAttribute("bloglist",articleService.search(keyword));
        model.addAttribute("categorylist",tagService.showTag());
        return "/admin/blog-list";
    }

    @RequestMapping("/admin/deleteComment/{commentId}")
    String deleteComment(@PathVariable("commentId") Integer commentId){
        articleService.deleteComment(commentId);
        return "redirect:/admin/comment-list.html";
    }



    @GetMapping(value={"/app/index.html","/"})
    String me(Model model){
        model.addAttribute("latestblog",articleService.showRecentArticle());
        model.addAttribute("user",userService.shwoUserInfo("weber"));
        return "app/index";
    }


    @GetMapping("/app/single/{titleId}")
    String blog(@PathVariable Integer titleId, Model model){
        Article article=articleService.showOneArticle(titleId);
        model.addAttribute("blog",article);
        model.addAttribute("comment",articleService.showComment(titleId));
        model.addAttribute("user",userService.shwoUserInfo("weber"));
        model.addAttribute("categories",tagService.showTag());
        model.addAttribute("Blogs",articleService.showRecentArticle());
        return "app/single";
    }

    @GetMapping("/app/blog.html")
    String blogList(Model model){
        Sort sort=new Sort(Sort.Direction.DESC,"time");
        Pageable pageable=new PageRequest(0,4,sort);
        model.addAttribute("bloglist",articleService.showAllArticle(pageable));
        model.addAttribute("user",userService.shwoUserInfo("weber"));
        model.addAttribute("categories",tagService.showTag());
        model.addAttribute("Blogs",articleService.showRecentArticle());
        model.addAttribute(pageable.getPageNumber());
        return "app/blog";
    }

    @GetMapping("/app/category/{id}")
    String cblogList(@PathVariable Integer id, Model model){
        Sort sort=new Sort(Sort.Direction.DESC,"time");
        Pageable pageable=new PageRequest(0,4,sort);
        model.addAttribute("bloglist",articleService.showTagArticle(id,pageable));
        model.addAttribute("user",userService.shwoUserInfo("weber"));
        model.addAttribute("categories",tagService.showTag());
        model.addAttribute("Blogs",articleService.showRecentArticle());
        return "app/blog";
    }

    @GetMapping("/app/contact.html")
    String contact(){
        return "app/contact";
    }

}