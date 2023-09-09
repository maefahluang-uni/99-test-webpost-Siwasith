package lab.webpost.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lab.webpost.domain.Post;
import lab.webpost.domain.User;

@DataJpaTest
public class PostResourceTest {

    @Autowired
    PostRepository repository;

    @BeforeEach
    public void setUp() {
        repository.save(new Post(1L, "Introduction to Spring Boot",
        "Spring Boot is a powerful framework for building Java applications.",
        LocalDateTime.parse("2023-07-13T12:30:00"), new User(1L)));
    }

    @Test
    public void testQuery(){
        List<Post> posts = repository.findByTitle("Introduction to Spring Boot");
        assertEquals(1, posts.size());
        assertEquals("Introduction to Spring Boot", posts.get(0).getTitle());
    }

    @Test
    public void testCreate(){
        Post post = new Post(2L, "RESTful APIs with Node.js", "Learn how to create RESTful APIs using Node.js and Express.",
        LocalDateTime.parse("2023-07-12T15:45:00"), new User(2L));
        repository.save(post);
        List<Post> posts = repository.findByTitle("RESTful APIs with Node.js");
        assertEquals(1, posts.size());
        assertEquals("RESTful APIs with Node.js", posts.get(0).getTitle());
    }

    @Test
    public void testDelete(){
        repository.deleteAll();
        List<Post> posts = repository.findAll();
        assertEquals(0, posts.size());
    }
}
