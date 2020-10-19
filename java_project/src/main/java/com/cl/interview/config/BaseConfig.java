package com.cl.interview.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BaseConfig {

    @Value("${web.file.path}")
    private String webFilePath;


    @Value("${web.image.path}")
    private String webImagePath;

    @Value("${web.image.url}")
    private String webImageUrl;
}
