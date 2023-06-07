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
