package com.roi.demos.reactive_demo.dao;

import com.roi.demos.reactive_demo.domain.Author;
import com.roi.demos.reactive_demo.domain.Course;
import com.roi.demos.reactive_demo.domain.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class CourseFauxRepository implements CourseDao, AuthorDao
{

    private final Logger log = LoggerFactory.getLogger(CourseFauxRepository.class);
    private final List<Course> fauxRepo;

    public CourseFauxRepository() {
        this.fauxRepo = new ArrayList<>();
    }

    public void saveAll(List<Course> additionalCourses){
        this.fauxRepo.addAll(additionalCourses);
        log.info(String.format("%s items saved to faux repo",additionalCourses.size()));
    }

    public Flux<Course> getCurrentCourses(){
        return Flux.fromIterable(this.fauxRepo);
    }

    @Override
    public Flux<Course> findCourseByTitleContaining(Search phrase) {
        return Flux.fromStream(
                this.fauxRepo.stream().filter(c->
                        c.getTitle().contains(phrase.getTitle()))
                        .peek(c->log.info("Found::"+c))
        );
    }

    @Override
    public Mono<Course> findCourseByTitle(Search srch) {
        return Mono.justOrEmpty(
                this.fauxRepo.stream()
                        .filter(c->c.getTitle().equalsIgnoreCase(srch.getTitle()))
                        .peek(c->log.info("Title search:"+c))
                        .findFirst());
    }

    private Stream<Author> getAuthorStream(){
        return fauxRepo.stream().map(Course::getAuthor);
    }

    public Flux<Author> findAllAuthors(){
        return Flux.fromStream(getAuthorStream());
    }

    @Override
    public Flux<Author> findAuthorByLastName(String lastName) {
        return Flux.fromStream(getAuthorStream()
                .filter(a -> a.getLastName().equalsIgnoreCase(lastName))
                .peek(t->log.info("lastname search:: "+t)));
    }

    @Override
    public Mono<Author> findAuthorByEmailAddress(String emailAddress) {
        return Mono.justOrEmpty(getAuthorStream()
                .filter(a->a.getEmailAddress().equalsIgnoreCase(emailAddress))
                .peek(t->log.info("lastname search:: "+t))
                .findFirst());
    }
}
