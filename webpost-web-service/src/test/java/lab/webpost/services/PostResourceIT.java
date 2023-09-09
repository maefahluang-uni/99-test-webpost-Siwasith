package lab.webpost.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lab.webpost.domain.Post;
import lab.webpost.domain.User;

import org.junit.Test;

public class PostResourceIT {
    private static Logger LOGGER = LoggerFactory.getLogger(PostResourceIT.class);

    private static String WEB_SERVICE_URI = "http://localhost:8080/posts";

    private static Client client;

    // List of Concerts.
    private static List<Post> posts = new ArrayList<>();

    // List of Concert URIs generated by the Web service. The Concert at
    // position i in concerts has the URI at position i in concertUris.
    private static List<Long> postUris = new ArrayList<>();

    @BeforeClass
    public static void createClient() {
        // Use ClientBuilder to create a new client that can be used to create
        // connections to the Web service.
        client = ClientBuilder.newClient();

        // Create some Posts.
        posts.add(new Post(1L, "Introduction to Spring Boot",
                "Spring Boot is a powerful framework for building Java applications.",
                LocalDateTime.parse("2023-07-13T12:30:00"), new User(1L)));
        posts.add(
                new Post(2L, "RESTful APIs with Node.js", "Learn how to create RESTful APIs using Node.js and Express.",
                        LocalDateTime.parse("2023-07-12T15:45:00"), new User(2L)));
        posts.add(new Post(3L, "Python Web Scraping", "A guide to web scraping using Python and BeautifulSoup.",
                LocalDateTime.parse("2023-07-11T10:15:00"), new User(3L)));
        posts.add(
                new Post(4L, "Getting Started with React", "Learn the basics of building web applications with React.",
                        LocalDateTime.parse("2023-07-10T08:00:00"), new User(4L)));
        posts.add(new Post(5L, "Introduction to SQL Databases",
                "An overview of SQL databases and their importance in modern applications.",
                LocalDateTime.parse("2023-07-09T14:20:00"), new User(5L)));

    }

    @AfterClass
    public static void closeConnection() {
        // After all tests have run, close the client.
        client.close();
    }

    @Before
    public void clearAndPopulate() {
        // Delete all Concerts in the Web service.
        Builder builder = client.target(WEB_SERVICE_URI).request();
        try (Response response = builder.delete()) {
        }

        // Clear Uris
        postUris.clear();

        // Populate the Web service with Posts.
        for (Post post : posts) {
            builder = client.target(WEB_SERVICE_URI).request(MediaType.APPLICATION_JSON);

            try (Response response = builder.post(Entity.json(post))) {
                LOGGER.warn("STATUS: " + response.getStatus());
            }
        }

        builder = client.target(WEB_SERVICE_URI).request();
        // get all concerts
        try (Response response = builder.get()) {
            List<Post> rposts = response.readEntity(new GenericType<List<Post>>() {
            });
            for (Post rpost : rposts) {
                postUris.add(rpost.getId());
            }
        }
    }

    @Test
    public void testCreate() {
        // Create a new Post.
        Post newpost = new Post(8L, "React State Management",
                "Explore different state management techniques in React applications.",
                LocalDateTime.parse("2023-07-06T13:20:00"), new User(4L));

        // Prepare an invocation on the Concert service
        Builder builder = client.target(WEB_SERVICE_URI).request(MediaType.APPLICATION_JSON);

        // Make the service invocation via a HTTP POST message, and wait for the
        // response.
        try (Response response = builder.post(Entity.json(newpost))) {

            // Check that the HTTP response code is 201 Created.
            int responseCode = response.getStatus();
            assertEquals(Response.Status.CREATED.getStatusCode(), responseCode);

        }
    }

    // test retrieve all posts
    @Test
    public void testRetrieve() {
        // Make an invocation on a Concert URI and specify JSON as the required data
        // format.
        Builder builder = client.target(WEB_SERVICE_URI).request()
                .accept(MediaType.APPLICATION_JSON);

        // Make the service invocation via a HTTP GET message, and wait for the
        // response.
        try (Response response = builder.get()) {

            // Check that the HTTP response code is 200 OK.
            int responseCode = response.getStatus();
            assertEquals(Response.Status.OK.getStatusCode(), responseCode);

            // Check that the posts is returned.
            List<Post> posts = response.readEntity(new GenericType<List<Post>>() {
            });
            assertEquals(5, posts.size());
        }
    }

    @Test
    public void testDelete() {
        Long postid = postUris.get(postUris.size() - 1);

        // Prepare an invocation on a Post URI.
        Builder builder = client.target(WEB_SERVICE_URI + "/" + postid).request();

        // Make the service invocation via a HTTP DELETE message, and wait for the
        // response.
        try (Response response = builder.delete()) {

            // Check that the DELETE request was successful.
            assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        }
        // Requery the Concert.
        try (Response response = client
                .target(WEB_SERVICE_URI + "/" + postid)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get()) {

            // Check that the GET request returns a 404 result.
            assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        }
    }

}
