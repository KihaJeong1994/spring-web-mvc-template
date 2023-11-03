package com.example.springwebmvctemplate.common.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration // should be added to enable annotation below
@EnableCaching
@RequiredArgsConstructor
@Slf4j
public class CacheConfig {
  private final CacheManager cacheManager;

  @Scheduled(cron = "0 0 0 * * *")
  public void evictUserCachesPerDay() {
    log.info("user cache evicted");
    cacheManager.getCache("user").clear();
  }
}
