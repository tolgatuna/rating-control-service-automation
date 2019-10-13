package com.duriancodes.ratingcontrolservice.automation.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RatingControlServiceConfig {
    @Value("${api.ratingService.endPoint}")
    private String ratingServiceEndPoint;
}
