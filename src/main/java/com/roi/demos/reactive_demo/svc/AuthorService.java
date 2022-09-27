package com.roi.demos.reactive_demo.svc;

import com.roi.demos.reactive_demo.domain.Author;
import com.roi.demos.reactive_demo.domain.SearchDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Flux<Author> getActiveAuthors();
    Flux<Author> getAuthorsByLastName(Mono<SearchDto> search);
    Mono<Author> getAuthorByEmailAddress(Mono<SearchDto> search);
}
