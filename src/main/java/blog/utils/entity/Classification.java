package blog.utils.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by weber on 2017/5/28.
 */
@Entity
@Table(name = "classification", catalog = "blogdev")
public class Classification {
    private int classId;
    private String classname;
    private Collection<Article> articlesByClassId;

    @Id
    @GeneratedValue
    @Column(name = "classID")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }


    @Column(name = "classname")
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "classificationByClassId")
    public Collection<Article> getArticlesByClassId() {
        return articlesByClassId;
    }

    public void setArticlesByClassId(Collection<Article> articlesByClassId) {
        this.articlesByClassId = articlesByClassId;
    }
}
