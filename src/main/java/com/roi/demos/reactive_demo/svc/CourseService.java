package com.roi.demos.reactive_demo.svc;

import com.roi.demos.reactive_demo.domain.Course;
import com.roi.demos.reactive_demo.domain.SearchDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Flux<Course> getCurrentCourses();
    Mono<Course> findCourseByTitle(Mono<SearchDto> search);
    Flux<Course> findCourseByTitleContaining(Mono<SearchDto> search);

    Mono<Course> findCourseByTitleContaining(String term);
}
