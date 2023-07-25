FROM openjdk:17
VOLUME /tmp
ARG DEPENDENCY=build/libs/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -cp app:app/lib/* com.example.springwebmvctemplate.SpringWebMvcTemplateApplication ${0} ${@}"]
