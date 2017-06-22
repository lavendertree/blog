package blog.utils.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by weber on 2017/5/28.
 */
@Entity
@Table(name = "user_reply", catalog = "blogdev")
public class UserReply {

    private int replyId;
    private String reply;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp replyTime;
    private VistorComment vistorCommentByCommentId;


    @Id
    @GeneratedValue
    @Column(name = "replyID")
    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    @Column(name = "reply")
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


    @Column(name = "reply_time")
    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentID", referencedColumnName = "commentID", nullable = false)
    public VistorComment getVistorCommentByCommentId() {
        return vistorCommentByCommentId;
    }

    public void setVistorCommentByCommentId(VistorComment vistorCommentByCommentId) {
        this.vistorCommentByCommentId = vistorCommentByCommentId;
    }
}

