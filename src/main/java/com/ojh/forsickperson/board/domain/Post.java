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
public class Post {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long viewCount;
    private Date createdDate;
    private Date modifiedDate;

    private List<Comment> comments;

    public Post(Long id, String title, String content, String writer, Date createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = 0L;
        this.createdDate = createdDate;
        this.modifiedDate = createdDate;
    }

}
