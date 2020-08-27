package com.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.annotation.RequestScope;

import com.exam.rest.client.RestTemplateClient;

@Configuration
public class Config {
  @Bean
  @RequestScope
  public RestTemplateClient restTemplateClient() {
    return RestTemplateClient.host("https://bpdts-test-app.herokuapp.com/")
        .addDefaultHeader(HttpHeaders.CONTENT_TYPE, "application/json");
  }
}
