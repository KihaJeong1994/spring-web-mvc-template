package com.example.springwebmvctemplate.common.feign;

import static com.example.springwebmvctemplate.common.feign.UserMocks.setupMockUserResponse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.springwebmvctemplate.domain.user.dto.UserDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
// @EnableFeignClients // causes overriding bean exception. I think it's because there is same
// annotation in main method
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
public class UserClientIntegrationTest {

  @Autowired private WireMockServer mockUserService;

  @Autowired private UserClient userClient;

  @BeforeEach
  void setUp() throws IOException {
    setupMockUserResponse(mockUserService);
  }

  @Test
  void whenGetUsers_thenUsersShouldBeReturned() {
    assertFalse(userClient.getUsers().isEmpty());
  }

  @Test
  void whenGetUsers_thenTheCorrectUsersShouldBeReturned() {
    assertTrue(
        userClient
            .getUsers()
            .containsAll(
                List.of(
                    new UserDto(1L, "jcorssenf@dyndns.org", "Henderson Coton", null),
                    new UserDto(2L, "gwerner4h@so-net.ne.jp", "Sargent Kopman", null))));
  }
}
