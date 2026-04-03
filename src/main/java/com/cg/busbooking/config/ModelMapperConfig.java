package com.cg.busbooking.config;

import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.entity.Trip;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing
 * ModelMapper mappings between entities and DTOs.
 */
@Configuration
@NoArgsConstructor
public class ModelMapperConfig {

    /** Provides a customized ModelMapper bean
     *  with mappings for Trip to TripResponseDto. */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Trip.class, TripResponseDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getRoute().getFromCity(), TripResponseDto::setFromCity);
                    mapper.map(src -> src.getRoute().getToCity(), TripResponseDto::setToCity);
                });

        return modelMapper;
    }
}
