package com.jojoldu.book.springboot.domain.posts;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "제목1";
        String content = "내용1";

        postsRepository.save(
            Posts.builder()
                .title(title)
                .content(content)
                .author("user1@gmail.com")
                .build());
        //when
        List<Posts> actual = postsRepository.findAll();
        //then
        Assertions.assertThat(actual.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(actual.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);

        System.out.println(">>> createdDate=" + posts.getCreatedDate() + ", modifiedDate="
            + posts.getModifiedDate());

        Assertions.assertThat(posts.getCreatedDate()).isAfter(now);
        Assertions.assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
