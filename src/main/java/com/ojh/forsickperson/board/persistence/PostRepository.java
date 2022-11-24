package com.ojh.forsickperson.board.persistence;

import com.ojh.forsickperson.board.domain.Post;
import lombok.NonNull;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {

    //key = postId (Long type)
    private static ConcurrentHashMap<Long, Post> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(); //0으로 초기화

    public Post findById(Long postId){
        return store.get(postId);
    }

    public List<Post> findAll(){
        return (List<Post>) store.values();
    }

    public long save(Post post){
        store.put(sequence.get(), post);
        return sequence.getAndIncrement();
    }

    public void update(@NonNull Long id, Post updateParam){
        Post findPost = findById(id);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
    }

    public void clearStore(){
        store.clear();
    }


}
