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
        comments.add(new Comment("comment content test", "test user 1", new Date(System.currentTimeMillis()),
                0L, 0L));


        for(Comment comment : comments){
            injectToPost(postRepository, comment);
        }
    }

    private static void injectToPost(PostRepository postRepository, Comment comment){
        Post post = postRepository.findById(comment.getPostId());
        post.getComments().add(comment);
    }
}
