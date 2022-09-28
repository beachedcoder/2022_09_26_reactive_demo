package com.roi.demos.reactive_demo.repository;

import com.roi.demos.reactive_demo.domain.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, UUID> {
}
