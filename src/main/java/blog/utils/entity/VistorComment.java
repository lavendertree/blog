package blog.utils.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by weber on 2017/6/19.
 */
@Entity
@Table(name = "vistor_comment" ,catalog = "blogdev")
public class VistorComment {
    private int commentId;
    private String vistorName;
    private String replyer;
    private String mail;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp commentTime;
    private Article articleByTitleId;

    @Id
    @GeneratedValue
    @Column(name = "commentID")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }


    @Column(name = "vistor_name")
    public String getVistorName() {
        return vistorName;
    }

    public void setVistorName(String vistorName) {
        this.vistorName = vistorName;
    }


    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "replyer")
    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }


    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Column(name = "comment_time")
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titleID", referencedColumnName = "titleID", nullable = false)
    public Article getArticleByTitleId() {
        return articleByTitleId;
    }

    public void setArticleByTitleId(Article articleByTitleId) {
        this.articleByTitleId = articleByTitleId;
    }
}
