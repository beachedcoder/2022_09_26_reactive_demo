package com.roi.demos.reactive_demo.controller;

import com.roi.demos.reactive_demo.domain.Course;
import com.roi.demos.reactive_demo.domain.SearchDto;
import com.roi.demos.reactive_demo.svc.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "api/course", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class CourseController {

    private final CourseService svc;

    public CourseController(CourseService svcRef) {
        this.svc = svcRef;
    }

    @GetMapping()
    public Flux<Course> getCourses(){
        return svc.getCurrentCourses();
    }

    @GetMapping(path = "{keyword}")
    public Mono<Course> getCourseByKeyword(@PathVariable("keyword") String term){
        return svc.findCourseByTitleContaining(term);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Course>> getCourseByTitle(@RequestBody Mono<SearchDto> search){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(svc.findCourseByTitle(search));
    }

    @PostMapping(path = "like",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCourseByTitleContaining(@RequestBody Mono<SearchDto> search){
        return ResponseEntity.ok(svc.findCourseByTitleContaining(search));
    }

}
