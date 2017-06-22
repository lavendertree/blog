package blog.utils.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by weber on 2017/6/19.
 */
@Entity
@Table(name = "vistor_comment" ,catalog = "blogdev")
public class VistorComment {
    private int commentId;
    private String vistorName;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp commentTime;
    private Collection<UserReply> userRepliesByCommentId;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "vistorCommentByCommentId")
    public Collection<UserReply> getUserRepliesByCommentId() {
        return userRepliesByCommentId;
    }

    public void setUserRepliesByCommentId(Collection<UserReply> userRepliesByCommentId) {
        this.userRepliesByCommentId = userRepliesByCommentId;
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
