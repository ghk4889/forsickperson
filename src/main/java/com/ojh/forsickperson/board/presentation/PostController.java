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
        ArrayList<Post> posts = new ArrayList<>();
        Post post0 = new Post("[입원] 택배 좀 대신 받아주실 분 구합니다!", "집은 부산 재송동에 있습니다. 현재 병원에 입원 중이라 받을 수가 없어요ㅠ", 10000, JobKind.SIMPLE,
                "김첨지", Date.valueOf("2022-10-10"));
        post0.setViewCount(30L);
        Post post1 = new Post("[시각 장애] 3일 동안 길 안내 부탁드립니다.", "이번에 일이 있어 서울로 올라가게 되었습니다. 초행이고 빠르게 일을 처리해야 해서 보조가 꼭 필요합니다. 부탁드립니다.", 300000, JobKind.COMPLICATED,
                "홍길동", Date.valueOf("2022-10-13"));
        post1.setViewCount(10L);
        Post post2 = new Post("[언어 장애] 목소리 좋으신 분 찾아요!", "세미나에서 발표를 해야 하는 데 언어 장애가 있어 말을 할 수 없습니다. 그래서 대신 발표해주실 분 찾아요..!", 200000, JobKind.SIMPLE,
                "김철수", Date.valueOf("2022-10-14"));
        post2.setViewCount(19L);
        Post post3 = new Post("[청각 장애] 대학 수업 대신 필기해주실 분 찾아요ㅠ", "최근에 사고로 인해 청각을 잃고 말았어요.. 지금 대학을 졸업하고 싶은데 수업을 들을 수가 없어요ㅠ", 250000, JobKind.SIMPLE,
                "한미미", Date.valueOf("2022-10-14"));
        post3.setViewCount(27L);
        Post post4 = new Post("[지체 장애] 등산 보조 찾습니다..", "저는 다리가 하나 없는 지체 장애인 입니다. 사고로 장애를 얻고 등산 한 번 못 가봤네요. 등산 도와주실 분 찾습니다..", 500000, JobKind.COMPLICATED,
                "신짱구", Date.valueOf("2022-10-15"));
        post4.setViewCount(8L);
        posts.add(post0);
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        for(Post post : posts) {
            postRepo.save(post);
        }

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
