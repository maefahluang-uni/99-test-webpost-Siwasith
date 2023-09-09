960331 Back-End Development (Spring Boot)
==========
Final Test - WebPost Application Development
---------

Test Time: 9 September 2023 13:00-15:00 (2 hours, total 20 points)

Before you start
----------
This test will assess your ability to design and implement a web application using the 3-tier architecture. You will be required to create entity classes representing the data model and develop RESTful services that connect to a JPA (Java Persistence API) for data storage and retrieval.

Begin by cloning this repository onto your local machine.
:octocat: :octocat: This project `webpost` includes the following submodules/prjects: `webpost-domain-model`, `webpost-web-service` and `webpost-web-front`.

Evaluation
----------
There are 2 test sets in this project. All these test sets are complete and **do not require any modification**. The source code of test sets can be found at project `webpost-web-service` under directory `/src/test`. You can run these test sets by maven's `verify` goal at the parent project `webpost`.
- Unit test `PostResourceTest` contains 3 test cases. Each test case worths 3 points.
- Unit test `PostResourceIT` contains 3 test cases. Each test case worths 3 points.
- Bonus 2 additional point when all tests in a test set pass.

## (a) Complete the domain model
In `webpost-domain-model`, the `Post` and `User` classes are included. This project serves database to the system using JPA. You need to add annotations to these classes. You may follow the following guideline:
- add `@Entity` to the entity class.
- add `@Id` and `@GeneratedValue` to the attribute that will be used as primary key.
- add relationship annotation to an attribute that link to the other class such as `@ManyToOne` with proper `fetch` and `cascade`  so that when a post is fetched, user is also fetched if existed. When a post is saved, its author is also saved. Many posts can be created by a user.

The object of these classes should be used to exchange between client and rest service in JSON format. So, please remember to pay attention on some attribute that are date/datetime such as `getCreatedAt()`. The  `@JsonSerialize` and `@JsonDeserialize` may need to be added (Please search on the internet how to use it).

## (b) Complete the rest service
The `webpost-web-service` contains the source code of rest services. You have to complete these services using JPA API to connect to database developed by `webpost-domain-model` project.

In `PostResource.java`, you have to complete the following services:
- add derived methods to retrieve all posts
- add derived methods to find post by title

In `UserResource.java`, you have to complete the following services:
- add derived methods to find user by username

In `PostController.java`, you have to complete the following services:

- `GET /posts/{id}`. Retrieves a representation of a `Post`, identified by its unique ID. The HTTP response message should have a status code of either 200 (OK) or 404 (File not found), depending on whether the specified post is found.

- `GET /posts`. Retrieves all `Post`. The HTTP response message should have a status code of 200 (OK) on success.

- `POST /posts`. Creates a `Concert`. The body of the HTTP request message contains a representation of the new post (other than the unique ID) to create. The service generates the concert's ID via the database, and returns a HTTP response of 201 (created).
  
- `DELETE /posts/{id}`. Delete a `Post`, where the concert is specified by a unique ID. This operation returns either 204 (no content) or 404, depending on whether the concert exists.

- `DELETE /posts`. Delete all `Post`s, and return a 204 (no content) status code.

In `UserController.java`, you have to complete the following services:

-  `GET /users/{username}`. Retrieve a representation of a `User`, identified by its `username`. The HTTP response message should have a status code of either 200 (OK) or 404 (File not found), depending on whether the specified post is found.


In `ConcertApplication.java`, modify the following
:smiley_cat: Add proper annotation like `@SpringBootApplication` and `EntityScan` that finds the entity classes in `webpost-domain-model` project.



## (c) Review the code in the web front
The front-end is developed using HTML, CSS and JavaScript in `webpost-web-front` project. You have to complete the following files. This project is compelted so you do not need to make any modification:
- review `login.html` that retrieves user information (like id of user) from rest service and store it in local storage.
- review `webpost.html` that allows user to post a new message and retrieve all posts from rest service to display them in the page.

***Please follow the TODO for guideline***

## (d) Build and Test the project
You can also build and run maven `verify` as usual at parent project `webpost`.




Run the project
----------
Please perform the following
- run the rest service at `webpost-web-service` using run button in ConcertApplication.
- run the front web application at `webpost-web-front` using maven with goal `jetty:run`.
- open browser and go to `http://localhost:8081/login.html` to see the result. You can login with username shown in `data.sql` file in `webpost-web-service`.
- try to add a new post and the new posts have be listed in the page.


 
#### Do not forget to Commit and Push code to github
