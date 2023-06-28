# Spring Web MVC template

https://docs.spring.io/spring-framework/reference/web/webmvc.html

Spring Web MVC : the web framework built on the ***Servlet*** API

## DispatcherServlet
- a central `Servlet`
- provides a shared algorithm for request processing
- actual work is performed by configurable delegate components(Special Bean Types)
  - `HandlerMapping` : map requests to a handler along with a list of interceptors for pre- and post- processing
  - `HandlerAdapter` : invoke a handler mapped to a request
  - `ViewResolver` : render the appropriate responses
  - `HandlerExceptionResolver` : resolve exceptions
  - ...
- expects a `WebApplicationContext` for its own configuration(ex. customized Special Bean Types)
- `DispatcherServlet` processes requests as follows
  1. search for WebApplicationContext, and bind it in the request
  2. bind various resolvers in the request
  3. search for appropriate handler & interceptors. If a handler is found, the execution chain associated with handler is run
  4. if model returned -> return view, else return response
  5. if an exception occurs during request mapping or in handler -> delegates to a chain of `HandlerExceptionResolver`
- you can implement `HandlerInterceptor` for handler interceptors
  - boolean preHandle(..)
  - void postHandle(..)
  - void afterCompletion(..)

## File upload, download

## Logging

## Exception Handling
- `RestControllerAdvice` : @ExceptionHandler method under class with `RestControllerAdvice` handles exception from all @Controller -> global
- you can use ProblemDetail, ErrorResponse to handle error with formal way  [RFC 7807](https://www.rfc-editor.org/rfc/rfc7807.html)

## Spring Rest Docs
Spring REST Docs uses snippets produced by tests written with Spring MVC's test framework, Spring Webflux's `WebTestClient`

If a snippet is incorrect, the test that produces it fails

pros
- can separate production code with documentation

cons
- compared to swagger, you need to write document by yourself(swagger automatically provides you test page)
- need test code
- test code with document code

## WebSocket
### Stomp
https://docs.spring.io/spring-framework/reference/web/websocket/stomp.html

[WebSocket protocol](https://datatracker.ietf.org/doc/html/rfc6455) : establish a full-duplex, two-way communication between client and server over a single TCP connection

1. HTTP Upgrade request
2. Protocol switch
3. After a successful handshake, the ***TCP socket*** underlying the HTTP upgrade request remains open for both the client and server

[STOMP(Simple Text Oriented Messaging Protocol)](https://stomp.github.io/stomp-specification-1.2.html) is a sub protocol to use on top of WebSocket to enable pub-sub messaging
- frame based
- I used apic google extension to test STOMP
```text
COMMAND
header1:value1
header2:value2

Body^@
```

COMMAND
- SEND
- SUBSCRIBE
- MESSAGE : broadcast messages to all subscribers