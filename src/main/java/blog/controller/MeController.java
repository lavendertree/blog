package blog.controller;

import blog.service.UserService;
import blog.utils.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weber on 2017/5/29.
 */
@RestController
public class MeController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    boolean addUser(String username,String password){
        if (username.equals("")||password.equals(""))
             return false;
        else{
            return userService.addUser(username, password);
        }
    }

    @PostMapping("/me")
    void updateInfo(UserInfo user){
        userService.updateUserInfo(user);
    }

    @PostMapping("/password")
    boolean changPassword(String oldPassword,String newPassword){
        return userService.updatePassword(oldPassword,newPassword);
    }

}
