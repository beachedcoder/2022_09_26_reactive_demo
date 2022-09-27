package com.roi.demos.reactive_demo.svc;

import com.roi.demos.reactive_demo.dao.CourseFauxRepository;
import com.roi.demos.reactive_demo.domain.Course;
import com.roi.demos.reactive_demo.domain.SearchDto;
import com.roi.demos.reactive_demo.util.DtoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseServiceFauxImpl implements CourseService{

    private final Logger log = LoggerFactory.getLogger(CourseServiceFauxImpl.class);
    private final CourseFauxRepository dao;

    public CourseServiceFauxImpl(CourseFauxRepository repoRef) {
        dao = repoRef;
    }

    @Override
    public Flux<Course> getCurrentCourses() {
        return dao.getCurrentCourses();
    }

    @Override
    public Mono<Course> findCourseByTitle(Mono<SearchDto> search) {
        return search.doOnNext(c->log.info("seeking course: " + c))
                .map(DtoUtils::toEntity)
                .flatMap(dao::findCourseByTitle);
    }

    @Override
    public Flux<Course> findCourseByTitleContaining(Mono<SearchDto> search) {
        return null;
    }
}
