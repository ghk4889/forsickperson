package com.ojh.forsickperson.board.persistence;

import com.ojh.forsickperson.board.domain.Post;
import org.springframework.stereotype.Repository;


import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {

    //key = postId (Long type)
    public ConcurrentHashMap<Long, Post> map = new ConcurrentHashMap<>();





}
