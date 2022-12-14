package com.ojh.forsickperson.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {

    private Long id;
    private String content;
    private String writer;
    private Date createdDate;

    private Long postId;
    private Long replyTargetId = 0L; //이 필드의 값은 어떤 댓글의 id 값임. 이 필드에 값이 있으면 지정한 댓글에 대한 댓글임.

    private List<Comment> comments;

    public Comment( String content, String writer, Date createdDate, Long postId, Long replyTargetId) {
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.postId = postId;
        this.replyTargetId = replyTargetId;
    }

}
