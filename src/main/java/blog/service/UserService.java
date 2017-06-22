package blog.service;

import blog.dao.UserInfoDao;
import blog.utils.authority.AuthorityTool;
import blog.utils.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by weber on 2017/5/21.
 */
@Service
public class UserService {


    @Autowired
    private UserInfoDao userInfoDao;


    //添加一个用户
    public boolean addUser(String username,String password){
        UserInfo user=userInfoDao.findOne(username);
        if(user!=null)
        return false;
        else {
            user = new UserInfo(username, new StandardPasswordEncoder().encode(password));
            userInfoDao.save(user);
            return true;
        }
    }


    //更改密码
    public boolean updatePassword(String oldPassword,String newPassword) {

        UserInfo user = userInfoDao.findOne(AuthorityTool.getPrincipal());
        if (new StandardPasswordEncoder().matches(oldPassword,user.getPassword())){
            user.setPassword(new StandardPasswordEncoder().encode(newPassword));
            userInfoDao.save(user);
            return true;
        }
        return  false;
    }





    //展示用户信息
    public UserInfo shwoUserInfo(String username){
        return userInfoDao.findOne(username);
    }

    //更新用户信息
    public void updateUserInfo(UserInfo user){
        UserInfo userDB = userInfoDao.findOne(AuthorityTool.getPrincipal());
        userDB.setName(user.getName());
        userDB.setSex(user.getSex());
        userDB.setBirth(user.getBirth());
        userDB.setCountry(user.getCountry());
        userDB.setCity(user.getCity());
        userDB.setProvince(user.getProvince());
        userDB.seteMail(user.geteMail());
        userDB.setPhone(user.getPhone());
        userDB.setQq(user.getQq());
        userDB.setInterest(user.getInterest());
        userInfoDao.save(userDB);
    }

}

