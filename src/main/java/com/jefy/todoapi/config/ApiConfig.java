package com.jefy.todoapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 17/06/2024
 */
@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApiConfig {
}
