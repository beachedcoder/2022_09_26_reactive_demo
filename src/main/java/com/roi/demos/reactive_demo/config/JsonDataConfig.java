package com.roi.demos.reactive_demo.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roi.demos.reactive_demo.dao.CourseFauxRepository;
import com.roi.demos.reactive_demo.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Configuration
public class JsonDataConfig {

    private final ObjectMapper mapper;
    Logger log = LoggerFactory.getLogger(JsonDataConfig.class);
    @Autowired
    private ApplicationContext ctx;

    public JsonDataConfig() {
        this.mapper = new ObjectMapper();
    }

    private List readJsonData(String fileName, TypeReference<?> valueRef) throws IOException {
        return (List) mapper.
                readValue(Files.newInputStream(ctx.getResource(fileName)
                        .getFile().toPath()), valueRef);
    }

    @Bean("loadJson")
    public CommandLineRunner initFauxCourseData(CourseFauxRepository repo,
                                                @Value("${faux.repo.courses}") String fileName){
        return args -> {
            List inboundList = null;
            try{
                inboundList = readJsonData(fileName, new TypeReference<List<Course>>(){});
            }
            catch (IOException e){
                log.error("failed during file read",e);
                throw new RuntimeException(e);
            }
            repo.saveAll(inboundList);
        };
    }
}
