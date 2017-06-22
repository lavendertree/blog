package blog.dao;

import blog.utils.entity.UserReply;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReplyDao extends JpaRepository<UserReply,Integer> {
}
