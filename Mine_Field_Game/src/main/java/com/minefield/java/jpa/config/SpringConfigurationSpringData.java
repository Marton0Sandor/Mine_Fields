package com.minefield.java.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.minefield.java.jpa.repository")
public class SpringConfigurationSpringData {

}
