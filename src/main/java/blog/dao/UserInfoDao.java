package blog.dao;


import blog.utils.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by weber on 2017/5/21.
 */
public interface UserInfoDao extends JpaRepository<UserInfo,String> {
    UserInfo findByUsername(String username);
}
