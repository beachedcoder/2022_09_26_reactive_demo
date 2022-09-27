package com.roi.demos.reactive_demo.domain.marshalling;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.roi.demos.reactive_demo.domain.Author;
import com.roi.demos.reactive_demo.domain.Course;

import java.io.IOException;
import java.util.UUID;

public class CourseDeserializer extends StdDeserializer<Course> {

    public CourseDeserializer() {
        this(null);
    }

    public CourseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Course deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode crsNode = p.readValueAsTree();
        return Course.builder()
                .id(UUID.fromString(crsNode.get("id").asText()))
                .title(crsNode.get("catalogTitle").asText())
                .description(crsNode.get("description").asText())
                .author(
                        Author.builder()
                                .id(UUID.fromString(crsNode.get("author").get("id").asText()))
                                .firstName(crsNode.get("author").get("firstName").asText())
                                .lastName(crsNode.get("author").get("lastName").asText())
                                .emailAddress(crsNode.get("author").get("emailAddress").asText())
                                .build()
                )
                .build();    }
}
