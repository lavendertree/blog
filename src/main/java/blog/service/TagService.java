package blog.service;

/**
 * Created by weber on 2017/6/19.
 */

import blog.dao.ArticleDao;
import blog.dao.ClassDao;
import blog.utils.entity.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weber on 2017/5/22.
 */
@Service
public class TagService {

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ArticleDao articleDao;

    //添加一个标签
    public void  addTag(String tagname ){
        Classification tag =new Classification();
        tag.setClassname(tagname);
        classDao.save(tag);
    }

    //删除一个标签
    public void deleteTag(Integer id){
        classDao.delete(id);
    }

    //展示所有标签
    public List<Classification> showTag(){
        return classDao.findAll();
    }


}

