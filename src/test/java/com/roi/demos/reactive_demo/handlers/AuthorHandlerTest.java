package com.roi.demos.reactive_demo.handlers;

import com.roi.demos.reactive_demo.dao.CourseFauxRepository;
import com.roi.demos.reactive_demo.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class AuthorHandlerTest {

    @Autowired
    ApplicationContext ctx;
    @Autowired
    CourseFauxRepository authorRepo;

    WebTestClient client;


    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToApplicationContext(ctx).build();
    }

    @Test
    void getHelloMessage(){
        client.get().uri("/api/func/hello").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().consumeWith(o-> System.out.println(o));
    }

    @Test
    void getAuthorsFunctionally(){
        client.get().uri("/api/author").exchange()
                .expectStatus().isAccepted()
                .expectBodyList(Author.class)
                .returnResult().getResponseBody();
    }
}