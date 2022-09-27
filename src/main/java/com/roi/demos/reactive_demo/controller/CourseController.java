package com.roi.demos.reactive_demo.controller;

import com.roi.demos.reactive_demo.domain.Course;
import com.roi.demos.reactive_demo.domain.SearchDto;
import com.roi.demos.reactive_demo.svc.CourseServiceFauxImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "api/course",produces = MediaType.TEXT_EVENT_STREAM_VALUE
        ,consumes = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseServiceFauxImpl svc;

    public CourseController(CourseServiceFauxImpl svcRef) {
        this.svc = svcRef;
    }

    @GetMapping()
    public Flux<Course> getCourses(){
        return svc.getCurrentCourses();
    }

    @PostMapping
    public ResponseEntity<Mono<Course>> getCourseByTitle(@RequestBody Mono<SearchDto> search){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(svc.findCourseByTitle(search));
    }

}
