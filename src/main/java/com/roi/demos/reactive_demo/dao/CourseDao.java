package com.roi.demos.reactive_demo.dao;

import com.roi.demos.reactive_demo.domain.Course;
import com.roi.demos.reactive_demo.domain.Search;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseDao {
    Flux<Course> findCourseByTitleContaining(Search entity);
    Mono<Course> findCourseByTitle(Search entity);
}
