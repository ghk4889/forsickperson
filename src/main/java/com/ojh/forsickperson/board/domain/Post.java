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
    private Integer pay;
    private JobKind jobKind;
    private String writer;
    private Long viewCount;
    private Date createdDate;


    private List<Comment> comments;

    public Post(String title, String content, Integer pay, JobKind jobKind,
                String writer, Date createdDate) {
        this.title = title;
        this.content = content;
        this.pay = pay;
        this.jobKind = jobKind;
        this.writer = writer;
        this.viewCount = 0L;
        this.createdDate = createdDate;
    }

}
