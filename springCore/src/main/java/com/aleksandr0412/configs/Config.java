package com.aleksandr0412.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.aleksandr0412.repo", "com.aleksandr0412.service"})
public class Config {
}