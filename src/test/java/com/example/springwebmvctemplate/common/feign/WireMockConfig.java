package com.example.springwebmvctemplate.common.feign;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class WireMockConfig {
  @Bean(initMethod = "start", destroyMethod = "stop")
  public WireMockServer mockUserService() {
    return new WireMockServer(37623);
  }
}
