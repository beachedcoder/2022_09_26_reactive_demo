package com.roi.demos.reactive_demo.config;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DbConfig extends AbstractR2dbcConfiguration {

    @Bean("cxFactory")
    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactoryBuilder.withOptions(
                builder()
                        .option(DRIVER,"h2")
                        .option(PROTOCOL,"mem")
                        .option(DATABASE,"mem")
                        .option(Option.valueOf("DB_CLOSE_DELAY"),"-1")
                        .option(Option.valueOf("DB_CLOSE_ON_EXIT"),"false")
                        .option(Option.valueOf("ACCESS_MODE_DATA"),"HSQLDB")
        ).build();
    }

    @Bean("dbinit")
    @DependsOn("cxFactory")
    public ConnectionFactoryInitializer initDb(ConnectionFactory cxf){
        ConnectionFactoryInitializer initCxf = new ConnectionFactoryInitializer();
        initCxf.setConnectionFactory(cxf);

        CompositeDatabasePopulator popularDb = new CompositeDatabasePopulator();
        popularDb.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("course_author_schema.sql")));
        popularDb.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("authors_db_data.sql")));
        popularDb.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("course_db_data.sql")));

        initCxf.setDatabasePopulator(popularDb);
        return initCxf;
    }
}
