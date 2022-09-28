package com.roi.demos.reactive_demo.repository;

import com.roi.demos.reactive_demo.domain.Author;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, UUID> {
    Flux<Author> findAllByLastName(String lastName);
    Mono<Author> findByEmailAddress(String  emailId);
}
