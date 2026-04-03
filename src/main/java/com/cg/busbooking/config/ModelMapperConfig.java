package com.cg.busbooking.config;

import com.cg.busbooking.dto.response.TripResponseDto;
import com.cg.busbooking.entity.Trip;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

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
