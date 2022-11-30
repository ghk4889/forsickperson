package com.ojh.forsickperson.board.presentation;

import com.ojh.forsickperson.board.domain.Comment;
import com.ojh.forsickperson.board.domain.Post;
import com.ojh.forsickperson.board.persistence.PostRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.ArrayList;


public class CommentInitializer {

    public static void init(PostRepository postRepository){
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1L, "저 재송동에서 살아요. 제가 하겠습니다.", "제애송", Date.valueOf("2022-10-10"),
                0L, 0L));
        comments.add(new Comment(2L, "감사합니다! 쪽지 보낼게요!", "김첨지(작성자)", Date.valueOf("2022-10-11"),
                0L, 1L));

        comments.add(new Comment(3L, "서울 살고 있는데 도와드릴까요?", "박서울", Date.valueOf("2022-10-13"),
                1L, 0L));
        comments.add(new Comment(4L, "혹시 강남쪽으로 와주실 수 있나요?", "홍길동(작성자)", Date.valueOf("2022-10-13"),
                1L, 3L));
        comments.add(new Comment(5L, "아.. 강남은 너무 멀어서.. 죄송합니다ㅠ", "박서울", Date.valueOf("2022-10-13"),
                1L, 3L));

        comments.add(new Comment(6L, "저 하고 싶은데 가능할까요??", "오목조", Date.valueOf("2022-10-14"),
                2L, 0L));
        comments.add(new Comment(7L, "쪽지로 음성 녹음 파일만 보내주세용", "김철수(작성자)", Date.valueOf("2022-10-14"),
                2L, 6L));
        comments.add(new Comment(8L, "저도 보냈습니다!", "김삼사", Date.valueOf("2022-10-14"),
                2L, 0L));

        comments.add(new Comment(8L, "제가 해드리겠습니다", "공수혁", Date.valueOf("2022-10-14"),
                3L, 0L));
        comments.add(new Comment(9L, "감사합니다ㅠ 쪽지로 전화번호 보내 주세용", "한미미(작성자)", Date.valueOf("2022-10-14"),
                3L, 8L));

        comments.add(new Comment(10L, "아무도 없나요ㅠ", "신짱구(작성자)", Date.valueOf("2022-10-15"),
                4L, 0L));
        
        for(Comment comment : comments){
            injectToPost(postRepository, comment);
        }
    }

    private static void injectToPost(PostRepository postRepository, Comment comment){
        Post post = postRepository.findById(comment.getPostId());
        post.getComments().add(comment);
    }
}
