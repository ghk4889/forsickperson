package com.ojh.forsickperson.board.presentation;

import com.ojh.forsickperson.board.domain.Post;
import com.ojh.forsickperson.board.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;


@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostRepository postRepo;


    @GetMapping("/")
    public String index(Model model){

        // totalRowCnt는 index.html에서 필요 없으므로 임의의 수를 줬다.
        List<Post> posts = postRepo.findAll();

        model.addAttribute("vipposts", posts);

        return "posts/vipposts";
    }


    //게시글 목록 페이지
    @GetMapping("/vipposts")
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



}
