package com.roi.demos.reactive_demo.controller;

import com.roi.demos.reactive_demo.domain.Author;
import com.roi.demos.reactive_demo.domain.Course;
import com.roi.demos.reactive_demo.svc.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourseControllerTest {

    private WebTestClient client;
    CourseService mockSvc;
    List<Course> mockData;
    @Autowired
    ApplicationContext ctx;


    @BeforeEach
    void setup(){
        mockData = Arrays.asList(
                Course.builder().id(UUID.randomUUID()).title("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build(),
                Course.builder().id(UUID.randomUUID()).title("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build(),
                Course.builder().id(UUID.randomUUID()).title("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build(),
                Course.builder().id(UUID.randomUUID()).title("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build()
        );
    }

    @AfterEach
    void cleaup(){
        client = null;
    }

    @Test
    void getCourses(){
        mockSvc = mock(CourseService.class);
        when(mockSvc.getCurrentCourses()).thenReturn(Flux.fromIterable(mockData));


        client = WebTestClient.bindToController(new CourseController(mockSvc))
                .configureClient().baseUrl("/api/course").build();

        client.get().uri("").exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void getCourseViaFakePathVariable(){
        mockSvc = mock(CourseService.class);
        when(mockSvc.getCurrentCourses()).thenReturn(Flux.fromIterable(mockData));
        when(mockSvc.findCourseByTitleContaining(anyString())).thenThrow(new IllegalArgumentException(""));

        client = WebTestClient.bindToController(new CourseController(mockSvc))
                .configureClient().baseUrl("/api/course").build();

        client.get().uri("").exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void getCoursesWithSpringContext(){

        WebTestClient altClient = WebTestClient.bindToApplicationContext(ctx).configureClient()
                .baseUrl("/api/course").build();
    }


}