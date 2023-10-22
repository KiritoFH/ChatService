package com.rehome.chat.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mp = new ModelMapper();
        mp.getConfiguration().setAmbiguityIgnored(true);
        return mp;
    }
}
