package lab.webpost.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lab.webpost.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
        // TODO: add necessary devied methods
        public List<Post> findAll();

        public List<Post> findByTitle(String title);
}
