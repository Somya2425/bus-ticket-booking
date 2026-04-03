package com.cg.busbooking.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining application-level beans.
 */
@Configuration
public class AppConfig {

    /** Provides a ModelMapper bean for object mapping. */
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}