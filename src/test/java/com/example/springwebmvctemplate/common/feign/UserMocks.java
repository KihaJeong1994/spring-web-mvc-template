package com.example.springwebmvctemplate.common.feign;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.StreamUtils.copyToString;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.IOException;
import org.springframework.http.HttpStatus;

public class UserMocks {
  public static void setupMockUserResponse(WireMockServer mockService) throws IOException {
    mockService.stubFor(
        WireMock.get(WireMock.urlEqualTo("/")) // should be same as mock uri, not this server uri
            .willReturn(
                WireMock.aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .withBody(
                        copyToString(
                            UserMocks.class
                                .getClassLoader()
                                .getResourceAsStream("payload/get-user-response.json"),
                            defaultCharset()))));
  }
}
