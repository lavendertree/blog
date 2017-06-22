package blog.utils.authority;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weber on 2017/5/26.
 */
public class AuthorityTool {

    /*
    *@function 检查是否已经登录，已登录返回true,否则返回false
    * @return true/false
     */
    public static boolean isAuthenticated(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null||authentication.getPrincipal().equals("annoymousUser")||!authentication.isAuthenticated())
            return false;
        else {
            return true;
        }
    }

    /*
    *@function 获取当前用户的所有权限
    *@return
     */
    public static List<String> authorities(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        List<String> authorities=new ArrayList<>();
        authentication.getAuthorities().forEach(a->authorities.add(a.getAuthority()));
        return  authorities;
    }

    /*
    *@function 获取当前用户名
    *@return 用户名
     */
    public static String getPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
