package blog.config.secure;

import blog.dao.UserInfoDao;
import blog.utils.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by weber on 2017/5/28.
 */

@Service
public class UserServiceDetails implements UserDetailsService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoDao.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("找不到该帐号");
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        return new User(username,user.getPassword(),authorities);
    }
}
