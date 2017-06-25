package blog.utils.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by weber on 2017/5/28.
 */
@Entity
@Table(name = "article", catalog = "blogdev")
public class Article {
    private int titleId;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp time;
    private String description;
    private String articleContent;
    private Classification classificationByClassId;
    private Collection<VistorComment> vistorCommentsByTitleId;
    private String photosrc;

    @Id
    @GeneratedValue
    @Column(name = "titleID")
    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }


    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "article_content")
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Column(name = "photo_src")
    public String getPhotosrc() {
        return photosrc;
    }

    public void setPhotosrc(String photosrc) {
        this.photosrc = photosrc;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classID", referencedColumnName = "classID")
    public Classification getClassificationByClassId() {
        return classificationByClassId;
    }

    public void setClassificationByClassId(Classification classificationByClassId) {
        this.classificationByClassId = classificationByClassId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "articleByTitleId")
    public Collection<VistorComment> getVistorCommentsByTitleId() {
        return vistorCommentsByTitleId;
    }

    public void setVistorCommentsByTitleId(Collection<VistorComment> vistorCommentsByTitleId) {
        this.vistorCommentsByTitleId = vistorCommentsByTitleId;
    }
}
