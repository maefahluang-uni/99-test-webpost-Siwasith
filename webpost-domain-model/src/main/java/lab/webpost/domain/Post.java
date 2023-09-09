package lab.webpost.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lab.jackson.LocalDateTimeDeserializer;
import lab.jackson.LocalDateTimeSerializer;

//TODO: add annotation for entity

public class Post {

    //TODO: add annotation for id
    private Long id;

    private String title;
    private String body;
    private LocalDateTime createdAt;

    private User author;

    
    public Post() {
    }
    public Post(Long id, String title, String body, LocalDateTime createdAt, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.author = author;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    //TODO: add the JSONSerialze and JsonDeserialize
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    
}
