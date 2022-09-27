package com.roi.demos.reactive_demo.svc;

import com.roi.demos.reactive_demo.dao.CourseFauxRepository;
import com.roi.demos.reactive_demo.domain.Author;
import com.roi.demos.reactive_demo.domain.SearchDto;
import com.roi.demos.reactive_demo.util.DtoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class AuthorServiceFauxImpl implements AuthorService{
    private final Logger log = LoggerFactory.getLogger(AuthorServiceFauxImpl.class);
    private final CourseFauxRepository dao;

    public AuthorServiceFauxImpl(CourseFauxRepository repoRef) {
        dao = repoRef;
    }

    @Override
    public Flux<Author> getActiveAuthors() {
        return dao.findAllAuthors();
    }

    @Override
    public Flux<Author> getAuthorsByLastName(Mono<SearchDto> search) {
        return search.doOnNext(s->log.info("seeing author: " +s))
                .map(DtoUtils::toEntity)
                .flatMapMany(s->dao.findAuthorByLastName(s.getAuthor()));
    }

    @Override
    public Mono<Author> getAuthorByEmailAddress(Mono<SearchDto> search) {
        return search.doOnNext(s->log.info("seeking author: " +s))
                .map(DtoUtils::toEntity)
                .flatMap(s->dao.findAuthorByEmailAddress(s.getAuthor()));
    }
}
