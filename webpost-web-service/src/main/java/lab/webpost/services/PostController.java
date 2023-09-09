package lab.webpost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lab.webpost.domain.Post;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    // TODO: get all Posts
    public ResponseEntity<List<Post>> getPosts() {
        return null;
    }

    //TODO: getting post by id
    public ResponseEntity<Post> getPostById( Long id) {
        // TODO: check if post is null
        return null;
    }

    //TODO: find by title
    public ResponseEntity<List<Post>> getPostByTitle( String title) {
        return null;
    }

    // TODO: adding new post
    public ResponseEntity<String> addPost( Post post) {
        return null;
    }

    // TODO: delete post by id
    public ResponseEntity<String> deletePost( Long id) {
        return null;
    }

    //TODO: delete all posts
    public ResponseEntity<String> deleteAllPosts() {
        return null;
    }

}
