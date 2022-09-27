package com.roi.demos.reactive_demo.dao;

import com.roi.demos.reactive_demo.domain.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorDao {
    Flux<Author> findAuthorByLastName(String lastName);
    Mono<Author> findAuthorByEmailAddress(String emailAddress);
}
