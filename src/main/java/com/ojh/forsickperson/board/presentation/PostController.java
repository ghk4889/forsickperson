package com.ojh.forsickperson.board.presentation;

import com.ojh.forsickperson.board.domain.JobKind;
import com.ojh.forsickperson.board.domain.Post;
import com.ojh.forsickperson.board.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostRepository postRepo;

    @PostConstruct
    public void init(){
        Post post = new Post("test title", "test content", 1000, JobKind.SIMPLE,
                "test user", new Date(System.currentTimeMillis()));

        postRepo.save(post);

        CommentInitializer.init(postRepo);
    }

    //게시글 목록 페이지
    @GetMapping({"/", "/vipposts"})
    public String posts(Model model){

        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);

        return "posts/vipposts";
    }

    //게시글 상세 페이지
    @GetMapping("/vipposts/{id}")
    public String post(Model model, @PathVariable long id){
        Post post = postRepo.findById(id);
        model.addAttribute("post", post);
        log.info("[findbyid]post = {}", post);
        return "posts/post";
    }

    //게시글 수정 폼 페이지
    @GetMapping("/vipposts/{id}/edit")
    public String editForm(Model model, @PathVariable long id){
        Post post = postRepo.findById(id);
        model.addAttribute("post", post);
        return "posts/editPost";
    }

    //게시글 등록 폼 페이지
    @GetMapping("/vipposts/new")
    public String addForm(Model model){
        return "posts/addPost";
    }

    //save, update, delete 처리

    //save
    @PostMapping("/api/vipposts")
    public String save(@ModelAttribute Post post){

        post.setViewCount(0L);
        post.setCreatedDate(new Date(System.currentTimeMillis()));
        log.info("[insert] post={}", post);

        //삽입한 게시글의 id 값이 반환됨.
        return "redirect:/vipposts/" + postRepo.save(post);
    }

    //update
    @PatchMapping("/api/vipposts/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Post updateParam){
        log.info("[update] post={}", updateParam);
        postRepo.update(id, updateParam);
        return "redirect:/vipposts/" + id;
    }

    //delete
    @DeleteMapping("/api/vipposts/{id}")
    public String delete(@PathVariable Long id){
        postRepo.delete(id);
        return "redirect:/";
    }


}
